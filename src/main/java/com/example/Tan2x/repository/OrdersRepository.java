package com.example.Tan2x.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Tan2x.entities.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long>{

}
