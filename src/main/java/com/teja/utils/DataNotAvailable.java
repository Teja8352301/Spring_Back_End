package com.teja.utils;

public class DataNotAvailable {

	String message;
	
	int code;
	
	String suggestion;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	public DataNotAvailable(String message, int code, String suggestion) {
		super();
		this.message = message;
		this.code = code;
		this.suggestion = suggestion;
	}

	public DataNotAvailable() {
		super();
	}
	
}
