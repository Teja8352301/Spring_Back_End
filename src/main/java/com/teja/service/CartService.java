

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
		Product product = (Product) productService.getProductService(productId);
		Cart cart = (Cart) getCartService(user.getCartId());
		List list = (List) cartDao.getCartItemByAllIds(productId, user.getCartId());
		if(list.size()>0) {
			CartItems cartItem = (CartItems) list.get(0);
			cartItem.setCartId(cart);
			int updatedQuantity = cartItem.getQuantity()+1;
			int updatedPrice = updatedQuantity * product.getPrice();
			cartItem.setQuantity(updatedQuantity);
			cartItem.setPrice(updatedPrice);
			cart.setTotalPrice(cart.getTotalPrice()+product.getPrice());
			return cartDao.addToCart(cartItem,cart);
			
		}
		else {
			CartItems cartItem = new CartItems();
			cartItem.setCartId(cart);
			product = (Product) productService.getProductService(productId);
			cartItem.setQuantity(1);
			cartItem.setProductTitle(product.getTitle());
			cartItem.setPrice(product.getPrice());
			cartItem.setProductId(product);
			cart.setTotalPrice(cart.getTotalPrice()+product.getPrice());
			return cartDao.addToCart(cartItem,cart);
		}	
	}
	
	public Object removeFromCartService(String productId,User user) {
		Product product = (Product) productService.getProductService(productId);
		Cart cart = (Cart) getCartService(user.getCartId());
		List list = (List) cartDao.getCartItemByAllIds(productId, user.getCartId());
		if(list.size()>0) {
			CartItems cartItem = (CartItems) list.get(0);
			if(cartItem.getQuantity()>1) {
			cartItem.setPrice(cartItem.getPrice()-product.getPrice());
			cartItem.setQuantity(cartItem.getQuantity()-1);
			cart.setTotalPrice(cart.getTotalPrice()-product.getPrice());
			return cartDao.addToCart(cartItem, cart);
			}
			else {
				cart.setTotalPrice(cart.getTotalPrice()-product.getPrice());
				return cartDao.deleteItemFromCart(cartItem, cart);
			}
		}
		else {
			return null;
		}
	}
	
	public Object getCartProductsService(User user) {
		return cartDao.getCartItemsByCartIdAndUserId(user.getCartId());
	}
	
	public void clearCartItemsAndOrder(List<CartItems> cartItemsList,Cart cart) {
		cartDao.clearCartItemsAndCart(cartItemsList, cart);
	}
}
