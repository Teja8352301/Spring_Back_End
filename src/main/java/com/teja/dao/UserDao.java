package com.teja.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.teja.entity.User;

@Repository
public class UserDao {

	@Autowired
	SessionFactory factory;
	
	@Transactional
	public Object createUser(User user) {
		Session session = factory.getCurrentSession();
		session.save(user);
		User newuser = session.get(User.class, user.getId());
		return newuser;
	}
	
	@Transactional
	public Object getUser(String id) {
		Session session = factory.getCurrentSession();
		User user = session.get(User.class, id);
		return user;
	}
	
	@Transactional
	public Object getByEmail(String email) {
		Session session = factory.getCurrentSession();
		String str = String.format("SELECT * FROM user WHERE email = '%s'",email);
		Query query = session.createNativeQuery(str,User.class);
		List<User> userLists = query.getResultList(); 
		return userLists.get(0);
	}
	
	@Transactional
	public Object getUserByIndex() {
		Session session = factory.getCurrentSession();
		Query query = session.createNativeQuery("SELECT * FROM user LIMIT 1", User.class);
		List<User> users = query.getResultList();
		return users.get(0);
	}
	
	@Transactional
	public Object deleteUser(String id) {
		Session session= factory.getCurrentSession();
		User user = session.get(User.class, id);
		session.delete(user);
		return new ResponseEntity<Object>(user,HttpStatus.FOUND);
	}
}
