package com.teja.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teja.dao.OrderDao;
import com.teja.entity.Cart;
import com.teja.entity.CartItems;
import com.teja.entity.OrderItems;
import com.teja.entity.Orders;
import com.teja.entity.User;

@Service
public class OrderService {
	
	@Autowired
	OrderDao orderDao;
	
	@Autowired
	CartService cartService;

	public void orderNowService(User user) {
		Orders order=new Orders(user);
		List<CartItems> cartItems = (List<CartItems>) cartService.getCartProductsService(user);
//		user.setOrders(order);
		List orderItemsList = new ArrayList<OrderItems>();
		for(CartItems cartItem:cartItems) {
			OrderItems orderItems = new OrderItems(cartItem.getProductTitle(),cartItem.getPrice(),cartItem.getQuantity(),order);
			orderItemsList.add(orderItems);
		}
		Cart cart = (Cart) cartService.getCartService(user.getCartId());
		cartService.clearCartItemsAndOrder(cartItems,cart);
		orderDao.addOrder(orderItemsList, order,user);
	}
	
	public Object getOrdersService(User user) {
		return user;
	}
	
	public Object getDetailOrderService(String id) {
		return orderDao.getDetailOrder(id);
	}
	
}
