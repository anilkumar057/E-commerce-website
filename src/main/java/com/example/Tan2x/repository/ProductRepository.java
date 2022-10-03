package com.example.Tan2x.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Tan2x.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	List<Product> findAllByCategory_Id(int id);
}
