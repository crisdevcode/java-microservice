package com.client.microservice.config.exception;

public class BadRequestException extends RuntimeException {

	public BadRequestException() {
		
	}
	
	public BadRequestException(String message) {
		super(message);
	}
}
