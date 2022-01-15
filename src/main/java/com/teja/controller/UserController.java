package com.teja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teja.entity.User;
import com.teja.service.UserService;

@RestController
@RequestMapping(value="/user")
@CrossOrigin
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping(value="/create")
	public Object createUser(@RequestBody User user) {
		Object object =  userService.createUser(user);
		return object;
	}
	
	@PostMapping(value="/email")
	public Object getUserByEmail(@RequestBody User user) {
		Object object =  userService.getUserByEmailId(user.getEmail());
		return object;
	}
	
	@DeleteMapping(value="/email/{id}")
	public Object deleteUserById(@PathVariable String id) {
		return userService.deleteUserById(id);
		
	}

}
