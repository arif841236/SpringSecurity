package com.indusnet.jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.indusnet.entity.MyUser;
import com.indusnet.entity.Role;
import com.indusnet.exception.UserException;
import com.indusnet.repo.UserRepo;

@Service
public class CustomUserDetails implements UserDetailsService {

	@Autowired
	private UserRepo uDao;
		
	public Collection<GrantedAuthority> customeGrantAuth(List<Role> roles){
		Collection<GrantedAuthority> collection = new ArrayList<>();
		roles.forEach(x -> 
			collection.add(new SimpleGrantedAuthority("ROLE_"+x.getRole()))
		);
		return collection;
		
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MyUser user = uDao.findByUsername(username);
		
		System.out.println(user);
		if(user!=null) {
			return new User(username, user.getPassword(),customeGrantAuth(user.getRoles()));
		}
		else {
			throw new UserException("user not existed by the username");
		}
		
	}

	
}
