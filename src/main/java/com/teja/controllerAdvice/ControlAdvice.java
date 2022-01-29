package com.teja.controllerAdvice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.teja.error.ErrorThrow;

class ErrorObject{
	
	String message;
	
	int statusCode;
	
	String suggestion;
	
	public ErrorObject() {
		super();
	}

	public ErrorObject(String message, int statusCode, String suggestion) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.suggestion = suggestion;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	
}


@ControllerAdvice
public class ControlAdvice {
	
	@ExceptionHandler
	public ResponseEntity<Object> exceptionsHandling(ErrorThrow err){
		ErrorObject errObject = new ErrorObject();
		errObject.setMessage(err.getMessage()); 
		errObject.setStatusCode(404);
		errObject.setSuggestion("Please login again");
		HttpHeaders header = new HttpHeaders();
		header.set("auth_logged", "false");
		return ResponseEntity.ok().headers(header).body(errObject);
	}
}
