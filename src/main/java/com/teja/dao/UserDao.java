package com.teja.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.teja.entity.AuthHeader;
import com.teja.entity.Cart;
import com.teja.entity.User;
import com.teja.service.AuthService;
import com.teja.service.CartService;
import com.teja.utils.DataNotAvailable;
import com.teja.utils.HttpHeadersList;
import com.teja.utils.JwtGenerator;
import com.teja.utils.SuccessLoggedUser;

@Repository
public class UserDao {

	@Autowired
	SessionFactory factory;
	

	@Autowired
	CartService cartService;
	
	@Autowired
	AuthService authService;
	
	@Autowired
	JwtGenerator jwtG;
	
	@Transactional
	public Object createUser(User user,HttpHeadersList headerMapList) {
		Session session = factory.getCurrentSession();
		session.save(user);
		User newuser = session.get(User.class, user.getId());
		AuthHeader authHeader = (AuthHeader) authService.setAuthService(newuser);
		Cart cart = new Cart();
		cart.setTotalPrice(0);
		cart.setUserId(newuser);
		cartService.createService(cart);
		newuser.setCartId(cart);
		session.saveOrUpdate(newuser);
		String jwtToken =  jwtG.jwtGenerator(newuser.getId(), newuser.getEmail(), authHeader.getAuthId());
		headerMapList.setHeaderMap("jwt_token", jwtToken);
		headerMapList.setHeaderMap("auth_logged", "true");
		headerMapList.setHeaderMap("authid", authHeader.getAuthId());
		SuccessLoggedUser successLogg = new SuccessLoggedUser("user successfully logged");
		return successLogg;
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
	
	@Transactional
	public Object validateUserDao(User user,HttpHeadersList headerMapList) {
		Session session= factory.getCurrentSession();
		String queryString = String.format("SELECT * FROM user where email='%s' and password='%s'",user.getEmail(),user.getPassword());
		Query query = session.createNativeQuery(queryString, User.class);
		List<User> userList = query.getResultList();
		if(userList.size()>0) {
			User newuser = userList.get(0);
			AuthHeader authHeader = (AuthHeader) authService.getAuthHeaderByUserId(newuser.getId());
			String jwtToken =  jwtG.jwtGenerator(newuser.getId(), newuser.getEmail(), authHeader.getAuthId());
			headerMapList.setHeaderMap("jwt_token", jwtToken);
			headerMapList.setHeaderMap("auth_logged", "true");
			headerMapList.setHeaderMap("authid", authHeader.getAuthId());
			SuccessLoggedUser successLogg = new SuccessLoggedUser("user successfully logged");
			return successLogg;
		}
		else {
			DataNotAvailable dna = new DataNotAvailable();
			dna.setCode(400);
			dna.setMessage("user not available");
			dna.setSuggestion("please sign up");
			headerMapList.setHeaderMap("auth_logged", "false");
			
			return dna;
		}
	}
}
