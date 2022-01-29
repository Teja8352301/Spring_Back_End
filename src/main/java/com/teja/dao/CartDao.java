package com.teja.dao;

import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.teja.entity.Cart;
import com.teja.entity.CartItems;
import com.teja.utils.HttpHeadersList;

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
	public Object addToCart(CartItems cartItem,Cart cart) {
	Session session = factory.getCurrentSession();
	session.clear();
	session.saveOrUpdate(cartItem);
	session.saveOrUpdate(cart);
		return this.getCartItemsByCartIdAndUserId(cart.getCartId());
	}
	
	@Transactional
	public Object getCartItemByAllIds(String productId,String cartId) {
		Session session = factory.getCurrentSession();
		String sqlString = String.format("SELECT * FROM cartitems WHERE productId='%s' and cartId='%s'",productId,cartId);
		NativeQuery<CartItems> query = session.createNativeQuery(sqlString, CartItems.class);
		List<CartItems> cartItems = query.getResultList();
		return cartItems;
	}
	
	@Transactional
	public Object getCartItemsByCartIdAndUserId(String cartId) {
		Session session = factory.getCurrentSession();
		String sqlString = String.format("SELECT * FROM cartitems WHERE cartId='%s'",cartId);
		NativeQuery<CartItems> query = session.createNativeQuery(sqlString, CartItems.class);
		List<CartItems> cartItems = query.getResultList();
		return cartItems;
	}
	
	@Transactional
	public Object deleteItemFromCart(CartItems cartItem,Cart cart) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(cart);
		session.delete(cartItem);
		return this.getCartItemsByCartIdAndUserId(cart.getCartId());
	}
	
	@Transactional
	public void clearCartItemsAndCart(List<CartItems> cartItemsList,Cart cart) {
		Session session = factory.getCurrentSession();
		cart.setTotalPrice(0);
		session.saveOrUpdate(cart);
		for(CartItems cartItem:cartItemsList) {
			session.delete(cartItem);
		}
	}
	
}
