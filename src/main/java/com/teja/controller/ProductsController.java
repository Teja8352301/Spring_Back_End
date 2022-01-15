package com.teja.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductsController {
	
	@Autowired
	public ProductService productService;
	
//---------------------------ADD PRODUCT--------------------------
	
	@PostMapping(value="/addProduct")
	public  Object addProduct(@RequestBody Product product,@RequestAttribute User userAuth) throws InterruptedException {
		Thread.sleep(3000);
		product.setUserId(userAuth);
		return productService.addProductService(product);
	}
	
//------------------------------------GET PRODUCT-------------------

	@GetMapping(value="/product/{productId}")
	public Object getProduct(@PathVariable String productId) throws InterruptedException {
		Thread.sleep(3000);
		return productService.getProductService(productId);
	}
		
//	-------------------------------DELETE PRODUCT--------------------
	
	@DeleteMapping(value="/product/{productId}")
	public Object deleteProduct(@PathVariable String productId) throws InterruptedException {
		Thread.sleep(3000);
		return productService.deleteProductService(productId);
	}
	
//	--------------------------------GET PRODUCTS------------------------
	
	@GetMapping(value="/getAllProducts")
	public Object getProducts() throws InterruptedException {
		Thread.sleep(3000);
		return productService.getProductsListService();
	}
	
	
}
