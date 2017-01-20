package com.programming4phone.airnow.collector;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.programming4phone.airnow.collector.entity.CurrentAirQuality;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

public class AirNowServiceTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Value("${local.server.port}")   
	int port;

	
	@Test
	public void test() {
		
		/*
		 * This test hits the live AirNow server. The airnow.key property in  
		 * the application.properties file must contain a valid API key.
		 * View this website for details:
		 * https://docs.airnowapi.org/faq 
		 */
		
		ResponseEntity<CurrentAirQuality> entity;
		
		entity = restTemplate
				.getForEntity("http://localhost:" + Integer.toString(port) + "/zipCodeObservation/282AA", CurrentAirQuality.class);
		assertTrue(entity.getStatusCode().equals(HttpStatus.BAD_REQUEST));
		
		entity = restTemplate
				.getForEntity("http://localhost:" + Integer.toString(port) + "/zipCodeObservation/282119", CurrentAirQuality.class);
		assertTrue(entity.getStatusCode().equals(HttpStatus.BAD_REQUEST));
		
		entity = restTemplate
				.getForEntity("http://localhost:" + Integer.toString(port) + "/zipCodeObservation/28211", CurrentAirQuality.class);
		assertTrue(entity.getStatusCode().equals(HttpStatus.OK));
		assertTrue(entity.getHeaders().containsKey("Access-Control-Allow-Origin"));
		CurrentAirQuality currentAirQuality = entity.getBody();
		assertNotNull(currentAirQuality);
		assertTrue("Charlotte".equalsIgnoreCase(currentAirQuality.getCity()));
	}

}
