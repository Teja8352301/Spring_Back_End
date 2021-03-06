package com.teja.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Repository;

import com.teja.entity.Product;
import com.teja.utils.HttpHeadersList;

@Repository
public class ProductDao {

	@Autowired
	SessionFactory factory;
	
	@Transactional
	public Object addProduct(Product product) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(product);
		return product;
	}
	
	@Transactional
	public Object getProduct(String id) {
		Session session = factory.getCurrentSession();
		Product product = session.get(Product.class, id);
		return product;
	}
	
	@Transactional
	public Object deleteProduct(String id) {
		Session session = factory.getCurrentSession();
		Product product = session.get(Product.class, id);
		session.delete(product);
		return product;
	}
	
	@Transactional
	public Object getProducts() {
		Session session = factory.getCurrentSession();
//		Query query = session.createNativeQuery("SELECT * FROM products");
//		List products = query.getResultList();
//		return products;
		Query query = session.createNativeQuery("SELECT * FROM products",Product.class );
		List products = query.getResultList();
		return products;
	}
}
