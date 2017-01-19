package com.programming4phone.airnow.collector;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.programming4phone.airnow.collector.entity.CurrentAirQuality;

public class ZipCodeValidatorTest {

	@Test
	public void test() {
		
		ZipCodeValidator zipCodeValidator = new ZipCodeValidator();
		
		Optional<ResponseEntity<CurrentAirQuality>>  test1 = zipCodeValidator.apply("28211");
		assertFalse(test1.isPresent());
		
		Optional<ResponseEntity<CurrentAirQuality>>  test2 = zipCodeValidator.apply("282AA");
		assertTrue(test2.isPresent());
		assertTrue(test2.get().getStatusCode().equals(HttpStatus.BAD_REQUEST));

	}

}
