package com.teja.dao;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.teja.entity.AuthHeader;

@Repository
public class AuthDao {
	
	@Autowired
	SessionFactory factory;

	@Transactional
	public Object getAuthUser(String authId) {
		Session session = factory.getCurrentSession();
		AuthHeader authHeader = session.get(AuthHeader.class, authId);
		return authHeader;
	}
	
	@Transactional
	public Object setAuthUser(AuthHeader authHeader) {
		Session session = factory.getCurrentSession();
		session.save(authHeader);
		return authHeader;
	}
	
	
	@Transactional
	public AuthHeader getAuthUserByUserId(String userId) {
		Session session = factory.getCurrentSession();
		String queryString = String.format("select * from authheaders where userId='%s'",userId);
		Query query = session.createNativeQuery(queryString, AuthHeader.class);
		List<AuthHeader> authHeaderList = query.getResultList();
		if(authHeaderList.size()>0) {
			return authHeaderList.get(0);
		}
		else {
			return null;
		}
	}
	
	
}
