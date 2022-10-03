package com.example.Tan2x.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Tan2x.entities.OrderItem;
import com.example.Tan2x.repository.OrderItemReposiroty;

@Service
public class OrderItemService {

	@Autowired
	OrderItemReposiroty orderItemReposiroty;
	
	public List<OrderItem> findAllItemByOrderId(long id){
		return orderItemReposiroty.findAllByOrderId(id);
	}
	
	public void addOrderItem(OrderItem orderItem) {
		orderItemReposiroty.save(orderItem);
	}
}
