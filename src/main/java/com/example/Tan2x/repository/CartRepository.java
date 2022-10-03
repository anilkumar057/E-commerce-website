package com.example.Tan2x.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Tan2x.entities.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{
	List<Cart> findAllByUserName(String userName); 
}
