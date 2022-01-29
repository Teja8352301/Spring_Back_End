package com.teja.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teja.entity.User;
import com.teja.service.OrderService;
import com.teja.utils.HttpHeadersList;

@RestController
@RequestMapping(value="/order")
@CrossOrigin
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@GetMapping(value="/orderNow")
	public Object orderNow(@RequestAttribute User userAuth,@RequestAttribute HttpHeadersList headerMapList) {
		Object object = orderService.orderNowService(userAuth);
		HashMap<String,String> hashMapList = headerMapList.getHeaderMap();
		BodyBuilder response = ResponseEntity.ok();
		for(Map.Entry m:hashMapList.entrySet()){  
			response.header(m.getKey().toString(), m.getValue().toString());
			   System.out.println(m.getKey()+" "+m.getValue());  
			  }
		return response.body(object);
	}
	
	@GetMapping(value="/getOrders")
	public Object getOrders(@RequestAttribute User userAuth,@RequestAttribute HttpHeadersList headerMapList) {
		Object object = orderService.getOrdersService(userAuth);
		HashMap<String,String> hashMapList = headerMapList.getHeaderMap();
		BodyBuilder response = ResponseEntity.ok();
		for(Map.Entry m:hashMapList.entrySet()){  
			response.header(m.getKey().toString(), m.getValue().toString());
			   System.out.println(m.getKey()+" "+m.getValue());  
			  }
		return response.body(object);
		
	}
	
	@GetMapping(value="/detailOrder/{id}")
	public Object getDetailOrder(@PathVariable String id,@RequestAttribute HttpHeadersList headerMapList) throws InterruptedException {
		Thread.sleep(3000);
		Object object = orderService.getDetailOrderService(id);
		HashMap<String,String> hashMapList = headerMapList.getHeaderMap();
		BodyBuilder response = ResponseEntity.ok();
		for(Map.Entry m:hashMapList.entrySet()){  
			response.header(m.getKey().toString(), m.getValue().toString());
			   System.out.println(m.getKey()+" "+m.getValue());  
			  }
		return response.body(object);
	}
}
