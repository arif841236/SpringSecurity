package com.indusnet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.indusnet.entity.MyUser;
import com.indusnet.entity.Role;
import com.indusnet.service.MyUserService;
import com.indusnet.service.RoleService;

@RestController
public class MyUserController {

	@Autowired
	private MyUserService myUserService;
	
	@Autowired
	private RoleService roleService;
	
	@PreAuthorize("hasAnyRole('ROLE_Admin')")
	@PostMapping("/addUser")
	public MyUser saveUser(@RequestBody MyUser user) {
		return myUserService.addUser(user);
	}
	@PreAuthorize("hasAnyRole('ROLE_Admin','ROLE_User','ROLE_Student')")
	@GetMapping("/getAllUser")
	public List<MyUser> fetchAllUser(){
		return myUserService.getAllUser();
	}
	

	@PreAuthorize("hasAnyRole('ROLE_Admin','ROLE_User')")
	@PostMapping("/addRole")
	public Role saveRole(@RequestBody Role role) {
		return roleService.addRole(role);
	}
	@PreAuthorize("hasAnyRole('ROLE_Admin','ROLE_User','ROLE_Stuent')")
	@GetMapping("/getAllRole")
	public List<Role> fetchAllRole(){
		return roleService.getAllRole();
	}
	
	
}
