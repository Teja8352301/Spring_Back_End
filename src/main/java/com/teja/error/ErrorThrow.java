package com.teja.error;

public class ErrorThrow extends Throwable{
	String message;

	public ErrorThrow(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
