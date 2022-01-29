package com.teja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.teja.dao.AuthDao;
import com.teja.entity.AuthHeader;
import com.teja.entity.User;

@Service
@CrossOrigin
public class AuthService {

	@Autowired
	AuthDao authDao;
	
	public Object getAuthService(String id) {
		return authDao.getAuthUser(id);
	}
	
	public Object setAuthService(User user) {
		AuthHeader authHeader = new AuthHeader();
		authHeader.setUser(user);
		return authDao.setAuthUser(authHeader);
	}
	
	public Object getAuthHeaderByUserId(String id) {
		return authDao.getAuthUserByUserId(id);
	}
	
}
