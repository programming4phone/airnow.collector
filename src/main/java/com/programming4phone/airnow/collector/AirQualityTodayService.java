package com.programming4phone.airnow.collector;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.programming4phone.airnow.collector.entity.CurrentAirQuality;

@Component
public class AirQualityTodayService implements Function<String, ResponseEntity<CurrentAirQuality>>{

	
	@Autowired
	AirNowResponseProcessor airNowResponseProcessor;
	
	/**
	 * Process the zip code and create the ResponseResponseEntity which
	 * will be returned to the invoker of the zipCodeObservation web service.
	 * @param zipcode String
	 * @return  ResponseEntity containing a CurrentAirQuality object
	 */
	@Override
	public ResponseEntity<CurrentAirQuality> apply(String zipcode) {
		/*
		 * Wrap the results in a ResponseEntity, which is returned by the controller.
		 */
		return  new ResponseEntity<CurrentAirQuality>(airNowResponseProcessor.get(zipcode),HttpStatus.OK);
	}

}
