package com.example.Tan2x.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Tan2x.entities.Role;
import com.example.Tan2x.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	RoleRepository roleRepository;
	
	public Optional<Role> findRoleById(int id){
		return roleRepository.findById(id);
	}
}
