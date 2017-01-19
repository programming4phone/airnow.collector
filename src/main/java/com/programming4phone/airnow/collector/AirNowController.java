package com.programming4phone.airnow.collector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.programming4phone.airnow.collector.entity.CurrentAirQuality;

@RestController
public class AirNowController {
	
	@Autowired
	AirNowService service;
	
	@Autowired
	ZipCodeValidator validator;
	
	/**
	 * Retrieve air quality data from Air Now and return it in a condensed format. The relevant HTTP headers
	 * to enable CORS are also returned, so that Javascript frameworks (like Angular) can invoke this web service.
	 * 
	 * @param zipCode String containing a zip code
	 * @return ResponseEntity containing a CurrentAirQuality object and the relevant Http Status code.
	 */
	@RequestMapping(value="/zipCodeObservation/{zipCode}", 
					method = RequestMethod.GET,
					produces={"application/json"})
	public ResponseEntity<CurrentAirQuality> getObservationByZipCode(@PathVariable("zipCode") String zipCode){
		
		/*
		 * Validate the zip code, then invoke the Air Now service.
		 */
		return validator.apply(zipCode).orElse(service.apply(zipCode));
		
	}
}
