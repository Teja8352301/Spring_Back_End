package com.teja.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
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
import com.teja.utils.HttpHeadersList;

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
	public Object createCart(@RequestAttribute User userAuth,@RequestAttribute HttpHeadersList headerMapList) {
		Cart cart = new Cart(0);
		userAuth.setCartId(cart);
		cart.setUserId(userAuth);
		Object object = cartService.createService(cart);
		HashMap<String,String> hashMapList = headerMapList.getHeaderMap();
		BodyBuilder response = ResponseEntity.ok();
		for(Map.Entry m:hashMapList.entrySet()){  
			response.header(m.getKey().toString(), m.getValue().toString());
			   System.out.println(m.getKey()+" "+m.getValue());  
			  }
		return response.body(object);
	}
	
	@GetMapping("/getCart")
	public Object getCart(@RequestAttribute User userAuth,@RequestAttribute HttpHeadersList headerMapList) {
		String cartId = userAuth.getCartId();
		Object object= cartService.getCartService(cartId);
		HashMap<String,String> hashMapList = headerMapList.getHeaderMap();
		BodyBuilder response = ResponseEntity.ok();
		for(Map.Entry m:hashMapList.entrySet()){  
			response.header(m.getKey().toString(), m.getValue().toString());
			   System.out.println(m.getKey()+" "+m.getValue());  
			  }
		return response.body(object);
	}
	
	@PostMapping("/addToCart")
	public Object addToCart(@RequestBody CartProduct product,@RequestAttribute User userAuth,@RequestAttribute HttpHeadersList headerMapList) {
		Object object= cartService.addToCartService(product.getProductId(), userAuth);
		HashMap<String,String> hashMapList = headerMapList.getHeaderMap();
		BodyBuilder response = ResponseEntity.ok();
		for(Map.Entry m:hashMapList.entrySet()){  
			response.header(m.getKey().toString(), m.getValue().toString());
			   System.out.println(m.getKey()+" "+m.getValue());  
			  }
		return response.body(object);
	}
	
	@GetMapping(value="/getCartProducts")
	public Object getCartProducts(@RequestAttribute User userAuth,@RequestAttribute HttpHeadersList headerMapList) throws InterruptedException {
		Thread.sleep(3000);
		Object object = cartService.getCartProductsService(userAuth);
		HashMap<String,String> hashMapList = headerMapList.getHeaderMap();
		BodyBuilder response = ResponseEntity.ok();
		for(Map.Entry m:hashMapList.entrySet()){  
			response.header(m.getKey().toString(), m.getValue().toString());
			   System.out.println(m.getKey()+" "+m.getValue());  
			  }
		return response.body(object);
	}
	
	@DeleteMapping("/deleteFromCart")
	public Object deleteFromCart(@RequestBody CartProduct product,@RequestAttribute User userAuth,@RequestAttribute HttpHeadersList headerMapList) {
		Object object = cartService.removeFromCartService(product.getProductId(), userAuth);
		HashMap<String,String> hashMapList = headerMapList.getHeaderMap();
		BodyBuilder response = ResponseEntity.ok();
		for(Map.Entry m:hashMapList.entrySet()){  
			response.header(m.getKey().toString(), m.getValue().toString());
			   System.out.println(m.getKey()+" "+m.getValue());  
			  }
		return response.body(object);
	}

}
