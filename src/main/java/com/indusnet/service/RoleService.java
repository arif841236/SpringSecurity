package com.indusnet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indusnet.entity.Role;
import com.indusnet.repo.RoleRepo;

@Service
public class RoleService {

	@Autowired
	private RoleRepo roleRepo;
	
	public Role addRole(Role role) {
		return roleRepo.save(role);
	}
	
	public List<Role> getAllRole(){
		return roleRepo.findAll();
	}
}
