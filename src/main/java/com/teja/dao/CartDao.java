package com.teja.dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.teja.entity.Cart;

@Repository
public class CartDao {
	
	@Autowired
	SessionFactory factory;

	@Transactional
	public Object createCart(Cart cart) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(cart);
		Cart newCart= session.get(Cart.class, cart.getCartId());
		return newCart;
	}
	
	@Transactional
	public Object getCart(String cartId) {
		Session session = factory.getCurrentSession();
		Cart cart = session.get(Cart.class, cartId);
		return cart;
	}
	
}
