package com.example.Tan2x.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Tan2x.entities.Product;
import com.example.Tan2x.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	public List<Product> getAllProduct(){
		return productRepository.findAll();
	}
	
	public void addProduct(Product product) {
		productRepository.save(product);
	}
	
	public void removeProductById(long id) {
		productRepository.deleteById(id);
	}
	
	public Optional<Product> getProductById(long id) {
		return productRepository.findById(id);
	}
	
	public List<Product> getAllProductByCategoryId(int id){
		return productRepository.findAllByCategory_Id(id);
	}
}
