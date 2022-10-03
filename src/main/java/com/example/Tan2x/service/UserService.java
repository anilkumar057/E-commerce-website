package com.example.Tan2x.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Tan2x.entities.User;
import com.example.Tan2x.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public void addUser(User user) {
		userRepository.save(user);
	}
}
