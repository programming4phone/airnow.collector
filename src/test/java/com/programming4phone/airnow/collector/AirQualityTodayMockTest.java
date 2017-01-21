package com.programming4phone.airnow.collector;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.programming4phone.airnow.collector.entity.CurrentAirQuality;
import com.programming4phone.airnow.collector.entity.ObservationCategory;
import com.programming4phone.airnow.collector.entity.ZipCodeObservation;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

public class AirQualityTodayMockTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Value("${local.server.port}")   
	int port;

	@MockBean
	private AirNowInvoker airNowInvoker;
	
	@Test
	public void testInternalServerError() {
		when(airNowInvoker.apply("54105")).thenAnswer(i->
			new ResponseEntity<List<ZipCodeObservation>>(new ArrayList<ZipCodeObservation>(),HttpStatus.INTERNAL_SERVER_ERROR)
		);
		
		ResponseEntity<CurrentAirQuality> entity;
		
		entity = restTemplate
				.getForEntity("http://localhost:" + Integer.toString(port) + "/zipCodeObservation/54105", CurrentAirQuality.class);
		assertTrue(entity.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR));
	}
	
	@Test
	public void testOK() {
		List<ZipCodeObservation> obs = new ArrayList<ZipCodeObservation>();
		obs.add(new ZipCodeObservation().setDateObserved("2017-01-21 ")
					.setHourObserved(14).setLocalTimeZone("EST").setReportingArea("Charlotte")
					.setStateCode("NC").setLatitude(35.227).setLongitude(-80.843)
					.setParameterName("O3").setAqi(11)
					.setCategory(new ObservationCategory().setNumber("1").setName("Good")));
		obs.add(new ZipCodeObservation().setDateObserved("2017-01-21 ")
				.setHourObserved(14).setLocalTimeZone("EST").setReportingArea("Charlotte")
				.setStateCode("NC").setLatitude(35.227).setLongitude(-80.843)
				.setParameterName("PM2.5").setAqi(56)
				.setCategory(new ObservationCategory().setNumber("1").setName("Good")));
		
		when(airNowInvoker.apply("28211")).thenAnswer(i->new ResponseEntity<List<ZipCodeObservation>>(obs,HttpStatus.OK));
		
		ResponseEntity<CurrentAirQuality> entity;
		
		entity = restTemplate
				.getForEntity("http://localhost:" + Integer.toString(port) + "/zipCodeObservation/28211", CurrentAirQuality.class);
		assertTrue(entity.getStatusCode().equals(HttpStatus.OK));

		assertTrue(entity.getHeaders().containsKey("Access-Control-Allow-Origin"));
		CurrentAirQuality currentAirQuality = entity.getBody();
		assertNotNull(currentAirQuality);
		assertTrue("Charlotte".equalsIgnoreCase(currentAirQuality.getCity()));
	}

}
