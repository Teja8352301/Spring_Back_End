package com.teja.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;


public class HttpHeadersList {

	HashMap<String,String> headersMap;

	public void setHeaderMap(String headerKey,String headerValue) {
		if(this.headersMap == null) {
			this.headersMap = new LinkedHashMap<>();
		}
		this.headersMap.put(headerKey, headerValue);
	}
	
	public HashMap<String,String> getHeaderMap(){
		return this.headersMap;
	}
	
	public void removeHeader(String key) {
		this.headersMap.remove(key);
	}
	
	
	
}
