package com.programming4phone.airnow.collector;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.programming4phone.airnow.collector.entity.ZipCodeObservation;

@Component
public class AirNowInvoker implements Function <String, ResponseEntity<List<ZipCodeObservation>>>{
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${airnow.key}")
    private String key;
	
	@Value("${airnow.distance}")
    private String distance;
	
	/**
	 * Invoke the Air Now web service.
	 * @param zipCode String 
	 * @return ResponseEntity containing the List of ZipCodeObservation objects
	 */
	public ResponseEntity<List<ZipCodeObservation>> apply(String zipCode){
		return restTemplate.exchange(
				buildAirNowUrl(zipCode),
		        HttpMethod.GET, 
		        null, 
		        new ParameterizedTypeReference<List<ZipCodeObservation>>() {});
	}
	
	/**
	 * Build an Air Now URL that will retrieve air quality information for a specific zip code.
	 * 
	 * @param zipCode String
	 * @return String containing the Air Now url 
	 * 
	 */
	private String buildAirNowUrl(String zipCode) {
		return new StringBuilder()
				.append("http://www.airnowapi.org/aq/observation/zipCode/current/?format=application/json&zipCode=")
				.append(zipCode)
				.append("&distance=")
				.append(distance)
				.append("&API_KEY=")
				.append(key)
				.toString();
	}
}
