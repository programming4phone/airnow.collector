package com.programming4phone.airnow.collector;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR, reason="Error invoking the Air Now web service")  
public class AirNowException extends RuntimeException{
	private static final long serialVersionUID = -4945500108409944543L;
	AirNowException(String message){
		super(message);
	}

}
