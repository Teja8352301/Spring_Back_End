package com.teja.error;

public class ErrorThrow extends RuntimeException{

	public ErrorThrow(String message, Throwable cause) {
		super(message, cause);
	}

	public ErrorThrow(String message) {
		super(message);
	}

	public ErrorThrow(Throwable cause) {
		super(cause);
	}
	
	
}
