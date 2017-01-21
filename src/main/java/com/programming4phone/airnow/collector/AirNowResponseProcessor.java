package com.programming4phone.airnow.collector;

import static java.util.concurrent.TimeUnit.HOURS;

import java.util.Date;
import java.util.List;

import javax.cache.CacheManager;
import javax.cache.annotation.CacheDefaults;
import javax.cache.annotation.CacheResult;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.Duration;
import javax.cache.expiry.TouchedExpiryPolicy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.programming4phone.airnow.collector.entity.CurrentAirQuality;
import com.programming4phone.airnow.collector.entity.ZipCodeObservation;

@Component
@CacheDefaults(cacheName = "zipcode")
public class AirNowResponseProcessor {
	/*
	 * Note: This class used to implement java.util.Function. However ehcache 
	 * would not work that way. 
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(AirNowResponseProcessor.class);
	
    @Component
    public static class CachingSetup implements JCacheManagerCustomizer {
      @Override
      public void customize(CacheManager cacheManager) {
        cacheManager.createCache("zipcode", new MutableConfiguration<>()
          .setExpiryPolicyFactory(TouchedExpiryPolicy.factoryOf(new Duration(HOURS, 1)))
          .setStoreByValue(false)
          .setStatisticsEnabled(true));
      }
    }

	public static final String OZONE = "O3";
	public static final String PARTICULATE_MATTER = "PM2.5";
	
	@Autowired
	private AirNowInvoker airNowInvoker;
	
	/**
	 * Invoke the Air Now web service and process the response. The list of ZipCodeObservation
	 * objects is reduced into a single CurrentAirQuality object.<br/><br/>
	 * The Air Now web service is updated once an hour and a rate limit
	 * of 500 invocations per hour is imposed. CurrentAirQuality results 
	 * are cached accordingly.
	 * @param zipCode String 
	 * @return ResponseEntity containing the CurrentAirQuality object and relevant Http Status code
	 * @throws com.programming4phone.airnow.collector.AirNowException if a failure occurs invoking the Air Now web service.
	 */
	@CacheResult
	public CurrentAirQuality get(String zipCode) {
		LOGGER.info("zipCode " + zipCode + " not found in cache. TimeStamp: {}", new Date());
		ResponseEntity<List<ZipCodeObservation>> airnowResponse = airNowInvoker.apply(zipCode);
		if(!airnowResponse.getStatusCode().equals(HttpStatus.OK)){
			String errorMessage = "Air Now HttpStatus: " + airnowResponse.getStatusCode().value() + " for " + zipCode;
			LOGGER.error(errorMessage);
			throw new AirNowException(errorMessage);
		}
		return buildCurrentAirQuality(airnowResponse).setZipCode(zipCode);
	}
	
	/**
	 * Extract the relevant details returned from the Air Now web service. The end user
	 * does not require all of the details returned from Air Now. If nothing is returned 
	 * from the Air Now web service, then all fields of the CurrentAirQuality object will
	 * be null.
	 * @param airnowResponse ResponseEntity containing a List of ZipCodeObservation objects.
	 * @return CurrentAirQuality
	 */
	private CurrentAirQuality buildCurrentAirQuality(ResponseEntity<List<ZipCodeObservation>> airnowResponse){
		
		CurrentAirQuality currentAirQuality = new CurrentAirQuality();
		
		// Extract the relevant information for Ozone (O3). 
		airnowResponse
			.getBody()
			.stream()
			.filter(z->OZONE.equalsIgnoreCase(z.getParameterName()))
			.findFirst()
			.ifPresent(z-> currentAirQuality
				.setCity(z.getReportingArea())
				.setState(z.getStateCode())
				.setLatitude(z.getLatitude())
				.setLongitude(z.getLongitude())
				.setOzoneAQI(z.getAqi()));
	
		// Extract the relevant information for Particulate Matter (PM2.5)
		airnowResponse
			.getBody()
			.stream()
			.filter(z->PARTICULATE_MATTER.equalsIgnoreCase(z.getParameterName()))
			.findFirst()
			.ifPresent(z-> currentAirQuality
				.setCity(z.getReportingArea())
				.setState(z.getStateCode())
				.setLatitude(z.getLatitude())
				.setLongitude(z.getLongitude())
				.setParticulateMatterAQI(z.getAqi()));
		
		return currentAirQuality;
	}


}
