package com.programming4phone.airnow.collector;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.programming4phone.airnow.collector.entity.CurrentAirQuality;

@Component
public class AirNowService implements Function<String, ResponseEntity<CurrentAirQuality>>{

	
	@Autowired
	AirNowCollector collector;
	
	@Override
	public ResponseEntity<CurrentAirQuality> apply(String zipcode) {
		/*
		 * Wrap the results in a ResponseEntity, which is returned by the controller.
		 */
		return  new ResponseEntity<CurrentAirQuality>(collector.get(zipcode),HttpStatus.OK);
	}

}
