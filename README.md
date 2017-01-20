# Air Now Collector Web Service

The primary purpose of this web service is to provide Air Quality readings for the [Air Quality Today] (https://air-quality-today-0100.herokuapp.com) Angular 2 application.

Air quality readings are supplied directly from the Environmental Protection Agency by the [Air Now API] (https://docs.airnowapi.org/webservices).

This code base adds to the Air Now API *Current Observations by Zip Code* web service by providing CORS response headers and caching of air quality readings.

## Using this web service

This web service is currently deployed on Heroku and can be accessed in a web browser using this link `https://protected-wildwood-44798.herokuapp.com/zipCodeObservation/{zipcode}` and substituting the `{zipcode}` value.

For example, to get the Air Quality readings for zip code 28211, use this link [https://protected-wildwood-44798.herokuapp.com/zipCodeObservation/28211] (https://protected-wildwood-44798.herokuapp.com/zipCodeObservation/28211).

Results for a particular zip code are cached for 1 hour and will only change if the EPA publishes a new reading!

## Air Now API Access

In order to run this web service, you must obtain an Air Now [API Key] (https://docs.airnowapi.org/faq) and use it to replace the value of the `airnow.key` property in the `application.properties` file.

## Development stack

This project was developed using Java 8, Spring Boot, and Ehcache.

## Build

Run `mvn clean install` to build the project and run the supplied unit tests. The build artifacts will be stored in the `target/` directory. 


