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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teja.entity.User;
import com.teja.service.CartService;
import com.teja.service.UserService;
import com.teja.utils.HttpHeadersList;

@RestController
@RequestMapping(value="/user")
@CrossOrigin
public class UserController {
	
	@Autowired
	UserService userService;
	
	
	@PostMapping(value="/create")
	public Object createUser(@RequestBody User user,@RequestAttribute HttpHeadersList headerMapList) throws InterruptedException {
		Thread.sleep(3000);
		Object object = userService.createUser(user,headerMapList);
		HashMap<String,String> hashMapList = headerMapList.getHeaderMap();
		BodyBuilder response = ResponseEntity.ok();
		for(Map.Entry m:hashMapList.entrySet()){  
			response.header(m.getKey().toString(), m.getValue().toString());
			   System.out.println(m.getKey()+" "+m.getValue());  
			  }
		return response.body(object);
	}
	
	@PostMapping(value="/email")
	public Object getUserByEmail(@RequestBody User user,@RequestAttribute HttpHeadersList headerMapList) {
		Object object =  userService.getUserByEmailId(user.getEmail());
		HashMap<String,String> hashMapList = headerMapList.getHeaderMap();
		BodyBuilder response = ResponseEntity.ok();
		for(Map.Entry m:hashMapList.entrySet()){  
			response.header(m.getKey().toString(), m.getValue().toString());
			   System.out.println(m.getKey()+" "+m.getValue());  
			  }
		return response.body(object);
	}
	
	@DeleteMapping(value="/email/{id}")
	public Object deleteUserById(@PathVariable String id,@RequestAttribute HttpHeadersList headerMapList) {
		Object object = userService.deleteUserById(id);
		HashMap<String,String> hashMapList = headerMapList.getHeaderMap();
		BodyBuilder response = ResponseEntity.ok();
		for(Map.Entry m:hashMapList.entrySet()){  
			response.header(m.getKey().toString(), m.getValue().toString());
			   System.out.println(m.getKey()+" "+m.getValue());  
			  }
		return response.body(object);
		
	}
	
	@PostMapping("/validate")
	public Object validateUser(@RequestBody User user,@RequestAttribute HttpHeadersList headerMapList) throws InterruptedException
	{
		Thread.sleep(3000);
		Object object =  userService.validateUserService(user,headerMapList);
		HashMap<String,String> hashMapList = headerMapList.getHeaderMap();
		BodyBuilder response = ResponseEntity.ok();
		for(Map.Entry m:hashMapList.entrySet()){  
			response.header(m.getKey().toString(), m.getValue().toString());
			   System.out.println(m.getKey()+" "+m.getValue());  
			  }
		return response.body(object);
	}

}
