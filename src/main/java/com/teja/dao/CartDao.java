package com.teja.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.teja.entity.Cart;
import com.teja.entity.CartItems;

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
	
	@Transactional
	public Object addToCart(CartItems cartItem) {
	Session session = factory.getCurrentSession();
	session.saveOrUpdate(cartItem);
		return cartItem;
	}
	
	@Transactional
	public Object getCartItemByAllIds(String productId,String userId,String cartId) {
		Session session = factory.getCurrentSession();
		String sqlString = String.format("SELECT * FROM cartitems WHERE productId='%s' and userId='%s' and cartId='%s'",productId,userId,cartId);
		NativeQuery<CartItems> query = session.createNativeQuery(sqlString, CartItems.class);
		List<CartItems> cartItems = query.getResultList();
		return cartItems;
	}
	
	@Transactional
	public Object getCartItemsByCartIdAndUserId(String userId,String cartId) {
		Session session = factory.getCurrentSession();
		String sqlString = String.format("SELECT * FROM cartitems WHERE userId='%s' and cartId='%s'",userId,cartId);
		NativeQuery<CartItems> query = session.createNativeQuery(sqlString, CartItems.class);
		List<CartItems> cartItems = query.getResultList();
		return cartItems;
	}
	
}
