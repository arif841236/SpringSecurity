package com.indusnet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.indusnet.entity.MyUser;
import com.indusnet.repo.UserRepo;

@Service
public class MyUserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public MyUser addUser(MyUser user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepo.save(user);
	}
	
	public List<MyUser> getAllUser(){
		return userRepo.findAll();
	}
}
