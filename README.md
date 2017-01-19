# Air Now Collector Web Service

The primary purpose of this web service is to provide Air Quality readings for the [Air Quality Today] (https://air-quality-today-0100.herokuapp.com) Angular 2 application.

Air quality readings are supplied directly from the Environmental Protection Agency by the [Air Now API] (https://docs.airnowapi.org/webservices).

This code base adds to the Air Now API `Current Observations by Zip Code` web service by providing CORS response headers and caching of air quality readings.

## Air Now API Access

In order to run this web service, you must obtain an Air Now [API Key] (https://docs.airnowapi.org/faq) and use it to replace the value of the `airnow.key` property in the `application.properties` file.

## Development stack

This project was generated with Java 8, Spring Boot, and Ehcache.

## Build

Run `mvn clean install` to build the project and run the supplied unit tests. The build artifacts will be stored in the `target/` directory. 


