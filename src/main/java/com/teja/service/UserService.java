package com.teja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teja.dao.UserDao;
import com.teja.entity.User;
import com.teja.utils.HttpHeadersList;

@Service
public class UserService {
	
	@Autowired
	UserDao userDao;
	
	
	public Object createUser(User user,HttpHeadersList headerMapList) {
//		User user = new User();
		return userDao.createUser(user,headerMapList);
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
	
	public Object validateUserService(User user,HttpHeadersList headerMapList) {
		return userDao.validateUserDao(user,headerMapList);
	}
}
