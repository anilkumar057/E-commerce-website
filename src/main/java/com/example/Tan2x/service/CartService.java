package com.example.Tan2x.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Tan2x.entities.Cart;
import com.example.Tan2x.repository.CartRepository;

@Service
public class CartService {
	
	@Autowired
	CartRepository cartRepository;
	
	public List<Cart> cartItemByUserName(String userName){
		return cartRepository.findAllByUserName(userName);
	}
	
	public void deleteCartById(long id) {
		cartRepository.deleteById(id);
	}
	
	public void addCart(Cart cart) {
		cartRepository.save(cart);
	}
}
