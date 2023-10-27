package com.example.ghumantu.Exception;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	@ExceptionHandler(value = {SpringException.class})
	public final ResponseEntity<ErrorMessage> somethingWentWrong(Exception e){
    	ErrorMessage exceptionResponse = new ErrorMessage(e.getMessage(),HttpStatus.BAD_REQUEST,ZonedDateTime.now());
    	
    	return new ResponseEntity<ErrorMessage>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }
}

class ErrorMessage{
	String message;
	//Throwable throwable;
	HttpStatus httpStatus;
	ZonedDateTime zonedDateTime;

	public ErrorMessage(String message, HttpStatus httpStatus, ZonedDateTime zonedDateTime) {
		super();
		this.message = message;
		
		this.httpStatus = httpStatus;
		this.zonedDateTime = zonedDateTime;
	}

	
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public ZonedDateTime getZonedDateTime() {
		return zonedDateTime;
	}

	public void setZonedDateTime(ZonedDateTime zonedDateTime) {
		this.zonedDateTime = zonedDateTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}