package com.flipkart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flipkart.exception.LoginException;
import com.flipkart.model.LoginDto;
import com.flipkart.model.User;
import com.flipkart.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;

	
	@PostMapping("/user")
	public ResponseEntity<String> registerUser(@RequestBody User user){
		
		String newUser = userService.register(user);
		
		return new ResponseEntity<String>(newUser, HttpStatus.CREATED);
	}
	
	@PostMapping("/user/login")
	public ResponseEntity<String> loginUser(@RequestBody LoginDto dto) throws LoginException{
		
		String logedIn = userService.logIn(dto);
		
		return new ResponseEntity<String>(logedIn, HttpStatus.CREATED);
	}
	
	@GetMapping("/user")
	public ResponseEntity<String> logoutUser(@RequestParam String uuid) throws LoginException{
		
		String user = userService.logout(uuid);
		
		return new ResponseEntity<String>(user, HttpStatus.CREATED);
	}
	
	
	
	
}
