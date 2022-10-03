package com.example.Tan2x.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Tan2x.entities.Orders;
import com.example.Tan2x.repository.OrdersRepository;

@Service
public class OrderService {

	@Autowired
	OrdersRepository ordersRepository;
	
	public Optional<Orders> findOrderById(long id) {
		return ordersRepository.findById(id);
	}
	
	public void addOrder(Orders orders) {
		ordersRepository.save(orders);
	}
}
