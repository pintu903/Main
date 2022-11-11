package com.flipkart.service;

import com.flipkart.exception.LoginException;
import com.flipkart.model.LoginDto;
import com.flipkart.model.User;

public interface UserService {

	public String register(User user);
	public String logIn(LoginDto dto) throws LoginException;
	public String logout(String uuid)  throws LoginException;
	
}
