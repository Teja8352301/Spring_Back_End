package com.teja.utils;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.teja.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGenerator {

	public String jwtGenerator(String userId,String email,String authIds) {
		String authId = authIds.replaceAll("-", "")+authIds.replaceAll("-", "");
		Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(authId), 
                SignatureAlgorithm.HS256.getJcaName());
		
		Instant now = Instant.now();
		String jwtToken = Jwts.builder()
		        .claim("userId", userId)
		        .claim("email", email)
		        .setId(UUID.randomUUID().toString())
		        .setIssuedAt(Date.from(now))
		        .signWith(hmacKey)
		        .compact();
		return jwtToken;
	}
	
	public Boolean jwtVerify(String userId,String email,String authIds,String jwtToken) {
		System.out.println("JWT TOKEN >>>"+jwtToken);
		String authId = authIds.replaceAll("-", "")+authIds.replaceAll("-", "");
		System.out.println("Auth Id>>"+authId);
		Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(authId), 
                SignatureAlgorithm.HS256.getJcaName());
		
		try {
		Jws<Claims> jwt = Jwts.parserBuilder()
	            .setSigningKey(hmacKey)
	            .build()
	            .parseClaimsJws(jwtToken);
		
		String jwsObject = jwt.toString();
		
		if(jwsObject.indexOf(userId)>-1 && jwsObject.indexOf(email)>-1) {
			return true;
		}
		else {
			return false;
		}
	}
	catch(Exception e) {
		return false;
	}
	}	
}
