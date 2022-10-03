package com.example.Tan2x.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Tan2x.entities.OrderItem;

public interface OrderItemReposiroty extends JpaRepository<OrderItem, Long>{

	List<OrderItem> findAllByOrderId(long orderId);
}
