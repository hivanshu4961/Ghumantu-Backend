package com.example.ghumantu.Exception;

public class SpringException extends RuntimeException {
	
	public SpringException(String message) {
		super(message);
	}
	public SpringException(String message,Exception e) {
		super(message,e);
	}
}
