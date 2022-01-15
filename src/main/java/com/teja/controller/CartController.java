package com.teja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teja.entity.Cart;
import com.teja.entity.User;
import com.teja.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	CartService cartService;
	
	@GetMapping("/create")
	public Object createCart(@RequestAttribute User userAuth) {
		Cart cart = new Cart(0);
		userAuth.setCartId(cart);
		cart.setUserId(userAuth);
		return cartService.createService(cart);
	}
	
	@GetMapping("/getCart")
	public Object getCart(@RequestAttribute User userAuth) {
		String cartId = userAuth.getCartId();
		return cartService.getCartService(cartId);
	}

}
