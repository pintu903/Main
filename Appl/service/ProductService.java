package com.flipkart.service;

import java.util.List;
import com.flipkart.exception.LoginException;
import com.flipkart.exception.ProductException;
import com.flipkart.model.Product;

public interface ProductService {

	public List<Product> viewProducts(String uuid) throws ProductException,LoginException;
	public List<Product> searchProducts(String productName,String uuid) throws ProductException,LoginException;
	public String addtoCart(Integer productId,String uuid) throws ProductException;
}
