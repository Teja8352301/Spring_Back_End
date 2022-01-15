package com.teja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teja.dao.CartDao;
import com.teja.entity.Cart;

@Service
public class CartService {
	
	@Autowired
	CartDao cartDao;
	
	public Object createService(Cart cart) {
		return cartDao.createCart(cart);
	}
	
	public Object getCartService(String id) {
		return cartDao.getCart(id);
	}
}
