package com.indusnet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.indusnet.dto.LoginResponce;
import com.indusnet.dto.UserRequest;
import com.indusnet.entity.MyUser;
import com.indusnet.exception.UserException;
import com.indusnet.jwt.JwtUtil;
import com.indusnet.repo.UserRepo;

@Service
public class UserService {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public LoginResponce loginUser(UserRequest user) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
			
		} catch (Exception e) {
			throw new UserException("User Not Authorized");
			// TODO: handle exception
		}
		
		String token = jwtUtil.generateToken(user.getUsername());

		MyUser myUser = userRepo.findByUsername(user.getUsername());
		return new LoginResponce(token, myUser);
	}
}
