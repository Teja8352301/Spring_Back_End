package com.teja.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teja.dao.CartDao;
import com.teja.entity.Cart;
import com.teja.entity.CartItems;
import com.teja.entity.Product;
import com.teja.entity.User;

@Service
public class CartService {
	
	@Autowired
	CartDao cartDao;
	
	@Autowired
	ProductService productService;
	
	public Object createService(Cart cart) {
		return cartDao.createCart(cart);
	}
	
	public Object getCartService(String id) {
		return cartDao.getCart(id);
	}
	
	public Object addToCartService(String productId,User user) {
		Product product;
		Cart cart = (Cart) getCartService(user.getCartId());
		List list = (List) cartDao.getCartItemByAllIds(productId, user.getId(), user.getCartId());
		if(list.size()>0) {
			CartItems cartItem = (CartItems) list.get(0);
			cartItem.setCartId(cart.getCartId());
			cartItem.setUserId(user.getId());
			int updatedQuantity = cartItem.getQuantity()+1;
			int updatedPrice = updatedQuantity * ((int)(cartItem.getPrice()/cartItem.getQuantity()));
			cartItem.setQuantity(updatedQuantity);
			cartItem.setPrice(updatedPrice);
			return cartDao.addToCart(cartItem);
			
		}
		else {
			CartItems cartItem = new CartItems();
			cartItem.setCartId(cart.getCartId());
			cartItem.setUserId(user.getId());
			product = (Product) productService.getProductService(productId);
			cartItem.setQuantity(1);
			cartItem.setProductTitle(product.getTitle());
			cartItem.setPrice(product.getPrice());
			cartItem.setProductId(productId);
			return cartDao.addToCart(cartItem);
		}	
	}
	
	public Object getCartProductsService(User user) {
		return cartDao.getCartItemsByCartIdAndUserId(user.getId(),user.getCartId());
	}
}
