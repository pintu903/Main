package com.flipkart.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flipkart.exception.LoginException;
import com.flipkart.exception.ProductException;
import com.flipkart.model.Product;
import com.flipkart.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/products")
	public ResponseEntity<List<Product>> seeProducts(@RequestParam String uuid) throws ProductException, LoginException{

		List<Product> viewProducts = productService.viewProducts(uuid);

		return new ResponseEntity<List<Product>>(viewProducts, HttpStatus.ACCEPTED);
	}

	@GetMapping("/product")
	public ResponseEntity<List<Product>> serachByProductName(@RequestParam String uuid, @RequestParam String productName) throws ProductException, LoginException{

		List<Product> viewProducts = productService.searchProducts(productName, uuid);

		return new ResponseEntity<List<Product>>(viewProducts, HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/product/cart")
	public ResponseEntity<String> serachByProductName(@RequestParam Integer id ,@RequestParam String productName) throws ProductException{

		String viewProducts = productService.addtoCart(id, productName);

		return new ResponseEntity<String>(viewProducts, HttpStatus.ACCEPTED);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
