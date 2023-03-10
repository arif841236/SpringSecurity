package com.indusnet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.indusnet.dto.LoginResponce;
import com.indusnet.dto.UserRequest;
import com.indusnet.service.UserService;

@RestController
public class UserLoginController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponce> login(@RequestBody UserRequest user) throws Exception{
		
		
		return ResponseEntity.ok().body(userService.loginUser(user));
	}
}
