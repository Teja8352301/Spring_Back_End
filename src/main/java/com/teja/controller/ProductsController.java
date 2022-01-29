package com.teja.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teja.entity.Product;
import com.teja.entity.User;
import com.teja.service.ProductService;
import com.teja.utils.HttpHeadersList;

@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductsController {
	
	@Autowired
	public ProductService productService;
	
//---------------------------ADD PRODUCT--------------------------
	
	@PostMapping(value="/addProduct")
	public  Object addProduct(@RequestBody Product product,@RequestAttribute User userAuth,@RequestAttribute HttpHeadersList headerMapList) throws InterruptedException {
		Thread.sleep(3000);
		Object object = productService.addProductService(product);
		HashMap<String,String> hashMapList = headerMapList.getHeaderMap();
		BodyBuilder response = ResponseEntity.ok();
		for(Map.Entry m:hashMapList.entrySet()){  
			response.header(m.getKey().toString(), m.getValue().toString());
			   System.out.println(m.getKey()+" "+m.getValue());  
			  }
		return response.body(object);
	}
	
//------------------------------------GET PRODUCT-------------------

	@GetMapping(value="/product/{productId}")
	public  Object getProduct(@PathVariable String productId,@RequestAttribute HttpHeadersList headerMapList) throws InterruptedException {
		Thread.sleep(3000);
		Object object = productService.getProductService(productId);
		HashMap<String,String> hashMapList = headerMapList.getHeaderMap();
		BodyBuilder response = ResponseEntity.ok();
		for(Map.Entry m:hashMapList.entrySet()){  
			response.header(m.getKey().toString(), m.getValue().toString());
			   System.out.println(m.getKey()+" "+m.getValue());  
			  }
		return response.body(object);
	}
		
//	-------------------------------DELETE PRODUCT--------------------
	
	@DeleteMapping(value="/product/{productId}")
	public Object deleteProduct(@PathVariable String productId,@RequestAttribute HttpHeadersList headerMapList) throws InterruptedException {
		Thread.sleep(3000);
		Object object = productService.deleteProductService(productId);
		HashMap<String,String> hashMapList = headerMapList.getHeaderMap();
		BodyBuilder response = ResponseEntity.ok();
		for(Map.Entry m:hashMapList.entrySet()){  
			response.header(m.getKey().toString(), m.getValue().toString());
			   System.out.println(m.getKey()+" "+m.getValue());  
			  }
		return response.body(object);
	}
	
//	--------------------------------GET PRODUCTS------------------------
	
	@GetMapping(value="/getAllProducts")
	public Object getProducts(@RequestAttribute HttpHeadersList headerMapList) throws InterruptedException {
		Thread.sleep(3000);
		List<Object>  object = (List<Object>) productService.getProductsListService();
		HashMap<String,String> hashMapList = headerMapList.getHeaderMap();
		BodyBuilder response = ResponseEntity.ok();
		for(Map.Entry m:hashMapList.entrySet()){  
			response.header(m.getKey().toString(), m.getValue().toString());
			   System.out.println(m.getKey()+" "+m.getValue());  
			  }
		return response.body(object);
		
	}
	
	
}
