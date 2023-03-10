package com.indusnet.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.indusnet.exception.UserException;


@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtutil;
	
	@Autowired
	private CustomUserDetails customUserDetails;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String authorizationHeader = request.getHeader("Authorization");
		
		String userName = null;
		String token = null;
		System.out.println("start.....................");
		System.out.println(authorizationHeader);
		
		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
			token = authorizationHeader.substring(7);
			
			try {
				userName = jwtutil.extractUsername(token);
				System.out.println("end...........................");
			} catch (IllegalArgumentException e) {
				throw new UserException("Unable to get JWT Token");
				
			}
			catch (Exception e) {
				throw new UserException("JWT Token has expired");
				
			}
		}
		else {
			logger.warn("JWT Token does not begin with Bearer String");
		}
		
		if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			UserDetails userDetails = customUserDetails.loadUserByUsername(userName);
			
			if(jwtutil.validateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
				new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				
			}
			
		}
		filterChain.doFilter(request, response);
		
	}

}
