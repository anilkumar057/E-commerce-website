package com.example.Tan2x.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.Tan2x.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findUserByMobile(String mobile);
}
