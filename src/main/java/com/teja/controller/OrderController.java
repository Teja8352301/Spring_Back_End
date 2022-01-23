package com.teja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teja.entity.User;
import com.teja.service.OrderService;

@RestController
@RequestMapping(value="/order")
@CrossOrigin
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@GetMapping(value="/orderNow")
	public void orderNow(@RequestAttribute User userAuth) {
		orderService.orderNowService(userAuth);
	}
	
	@GetMapping(value="/getOrders")
	public Object getOrders(@RequestAttribute User userAuth) {
		return orderService.getOrdersService(userAuth);
		
	}
	
	@GetMapping(value="/detailOrder/{id}")
	public Object getDetailOrder(@PathVariable String id) throws InterruptedException {
		Thread.sleep(3000);
		return orderService.getDetailOrderService(id);
	}
}
