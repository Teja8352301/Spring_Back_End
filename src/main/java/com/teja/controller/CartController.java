package com.teja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teja.entity.Cart;
import com.teja.entity.CartItems;
import com.teja.entity.Product;
import com.teja.entity.User;
import com.teja.service.CartService;

class CartProduct{
	
	String productId;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	
}

@RestController
@RequestMapping("/cart")
@CrossOrigin
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
	
	@PostMapping("/addToCart")
	public Object addToCart(@RequestBody CartProduct product,@RequestAttribute User userAuth) {
		return cartService.addToCartService(product.getProductId(), userAuth);
	}
	
	@GetMapping(value="/getCartProducts")
	public Object getCartProducts(@RequestAttribute User userAuth) throws InterruptedException {
		Thread.sleep(3000);
		return cartService.getCartProductsService(userAuth);
	}
	
	@DeleteMapping("/deleteFromCart")
	public Object deleteFromCart(@RequestBody CartProduct product,@RequestAttribute User userAuth) {
		return cartService.removeFromCartService(product.getProductId(), userAuth);
	}

}
