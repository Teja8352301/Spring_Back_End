package com.teja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teja.dao.UserDao;
import com.teja.entity.User;

@Service
public class UserService {
	
	@Autowired
	UserDao userDao;
	
	public Object createUser(User user) {
//		User user = new User();
		Object userObject = userDao.createUser(user);
		return userObject;
	}
	
	public Object getUserByEmailId(String email) {
		Object userObject = userDao.getByEmail(email);
		return userObject;
	}
	
	public Object getUserByIndex() {
		Object object = userDao.getUserByIndex();
		return object;
	}
	
	public Object deleteUserById(String id) {
	return userDao.deleteUser(id);	
	}
}
