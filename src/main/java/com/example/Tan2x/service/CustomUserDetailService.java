package com.example.Tan2x.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Tan2x.controller.HomeController;
import com.example.Tan2x.entities.CustomUserDetail;
import com.example.Tan2x.entities.User;
import com.example.Tan2x.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	HomeController homeController;
	
	@Override
	public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {
		// TODO Auto-generated method stu
		homeController.postLogin();
		display();
		Optional<User> user = userRepository.findUserByMobile(mobile);
		user.orElseThrow(() -> new UsernameNotFoundException("User does not exits"));
		return user.map(CustomUserDetail::new).get();
	}
	
	void display() {
		System.out.println("this is just testing that the method inside userDetails is running");
//		HttpResponse<String> response = Unirest.get("http://2factor.in/API/V1/293832-67745-11e5-88de-5600000c6b13/SMS/991991199/AUTOGEN")
//				  .header("content-type", "application/x-www-form-urlencoded")
//				  .asString();
	}

}
