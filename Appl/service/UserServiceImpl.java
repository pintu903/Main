package com.flipkart.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flipkart.exception.LoginException;
import com.flipkart.model.CurrentUserSession;
import com.flipkart.model.LoginDto;
import com.flipkart.model.User;
import com.flipkart.repository.CurrentUserSessionDao;
import com.flipkart.repository.UserDao;

import net.bytebuddy.utility.RandomString;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userdao;
	
	@Autowired
	private CurrentUserSessionDao currentUserSessionDao;

	@Override
	public String register(User user) {
		
		if(user ==  null) 
			throw new IllegalArgumentException("Incorrect details");
		
		userdao.save(user);
		return "User saved successfully";
	}

	@Override
	public String logIn(LoginDto dto) throws LoginException{
		
		User user = userdao.findByUserEmail(dto.getEmail());
		
		if(user==null)
			throw new LoginException("Please Enter valid email");
		
		Optional<CurrentUserSession> existedUser = currentUserSessionDao.findById(user.getId());
		
		if(existedUser.isPresent()) {
			throw new LoginException("User is already logedIn");
		}
		
		if(user.getEmail().equals(dto.getEmail()) && user.getPassword().equals(dto.getPassword())) {
			
			CurrentUserSession currentUserSession = new CurrentUserSession(user.getId(), RandomString.make(6), LocalDateTime.now());
			
			currentUserSessionDao.save(currentUserSession);
			
			return "Login successfully";
		}
		else
			throw new LoginException("Please Enter a valid password!");
	}

	@Override
	public String logout(String uuid) throws LoginException {
		
		CurrentUserSession user = currentUserSessionDao.findByUuid(uuid);
		
		if(user == null) {
			throw new LoginException("User doesn't exist");
		}
		
		currentUserSessionDao.delete(user);
		
		return "Logout successfully";	
	}
}
