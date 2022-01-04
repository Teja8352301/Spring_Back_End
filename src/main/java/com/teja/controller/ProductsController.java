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

import com.teja.entity.Product;
import com.teja.service.ProductService;

@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductsController {
	
	@Autowired
	public ProductService productService;
	
//---------------------------ADD PRODUCT--------------------------
	
	@PostMapping(value="/addProduct")
	public  Object addProduct(@RequestBody Product product) throws InterruptedException {
		Thread.sleep(3000);
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
