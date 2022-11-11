package com.flipkart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flipkart.exception.LoginException;
import com.flipkart.exception.ProductException;
import com.flipkart.model.CurrentUserSession;
import com.flipkart.model.Product;
import com.flipkart.model.User;
import com.flipkart.repository.CurrentUserSessionDao;
import com.flipkart.repository.ProductDao;
import com.flipkart.repository.UserDao;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private CurrentUserSessionDao currentUserSessionDao;

	@Override
	public List<Product> viewProducts(String uuid) throws ProductException,LoginException{

		CurrentUserSession user = currentUserSessionDao.findByUuid(uuid);

		if(user == null) throw new LoginException("Please register to see products");

		List<Product> products = productDao.findAll();

		if(products.size() != 0) return products;

		else 
			throw new ProductException("There is no products available");
	}

	@Override
	public List<Product> searchProducts(String productName,String uuid) throws ProductException,LoginException{
		CurrentUserSession user = currentUserSessionDao.findByUuid(uuid);

		if(user == null) throw new LoginException("Please register to see products");

		List<Product> products = productDao.findByProductName(productName);

		if(products.size() != 0) return products;

		else 
			throw new ProductException("There is no products available");
	}

	@Override
	public String addtoCart(Integer productId, String uuid) throws ProductException {

		CurrentUserSession user = currentUserSessionDao.findByUuid(uuid);

		if(user == null) throw new ProductException("Please register to see products");
		
		Optional<User> customer = userDao.findById(user.getUserId());
		
		customer.get().getCart().add(productDao.getById(productId));

		return "Product added to cart";
	}






}
