package com.flipkart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flipkart.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer>{

	public User findByUserEmail(String userEmail);
}
