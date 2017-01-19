package com.programming4phone.airnow.collector;

import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.programming4phone.airnow.collector.entity.CurrentAirQuality;

@Component
public class ZipCodeValidator implements Function<String,Optional<ResponseEntity<CurrentAirQuality>>>{

	final static String zipcodeRegex = "^[0-9]{5}";
	 
	Pattern pattern = Pattern.compile(zipcodeRegex);
	
	/**
	 * The zip code is validated as a simple 5 digit zip code. Air Now will not accept anything else.
	 * If the zip code is valid an empty Optional is returned. If the zip code is invalid, a
	 * response entity containing Http Status 400 (BAD_REQUEST) is returned. 
	 * 
	 * @param zipCode String
	 * @return Optional of ResponseEntity containing an CurrentAirQuality object.
	 */
	@Override
	public Optional<ResponseEntity<CurrentAirQuality>> apply(String zipCode) {
		Matcher matcher = pattern.matcher(zipCode);
		return matcher.matches() ? 
				Optional.empty() :
				Optional.ofNullable(new ResponseEntity<CurrentAirQuality>(new CurrentAirQuality(),HttpStatus.BAD_REQUEST));
	}

}
