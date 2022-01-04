package com.teja.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teja.dao.ProductDao;
import com.teja.entity.Product;


@Service
public class ProductService {
	
	@Autowired
	ProductDao productDao;
	
	public Object addProductService(Product product) {
		Product addproduct = (Product)productDao.addProduct(product);
		return addproduct;
	}
	
	public Object getProductService(String id) {
		Product getproduct = (Product)productDao.getProduct(id);
		return getproduct;
	}
	
	public Object getProductsListService() {
		return productDao.getProducts();
	}
	
	public Object deleteProductService(String id) {
		Product deletedproduct = (Product)productDao.deleteProduct(id);
		return null;
	}
}
