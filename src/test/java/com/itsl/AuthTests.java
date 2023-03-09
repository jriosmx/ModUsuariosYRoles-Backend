package com.itsl;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;


import com.itsl.controllers.AuthController;

import com.itsl.security.jwt.JwtUtils;

public class AuthTests {
	
	private static AuthenticationManager am = new SampleAuthenticationManager();
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	AuthController authController;

	@Test
	public void testSignin() {
		
		try {
	        Authentication request = new UsernamePasswordAuthenticationToken("Juanito", "Cuenta");
	        System.out.println("request: "+request);
	        
	        Authentication result = am.authenticate(request);
	        SecurityContextHolder.getContext().setAuthentication(result);
	      } catch(AuthenticationException e) {
	        System.out.println("Authentication failed: " + e.getMessage());
	      }
	    
	    System.out.println("Successfully authenticated. Security context contains: " +
	              SecurityContextHolder.getContext().getAuthentication());
	}
	
}

class SampleAuthenticationManager implements AuthenticationManager {
		  static final List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();

		  static {
			  AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_USER"));
			  AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		  }

		  public Authentication authenticate(Authentication auth) throws AuthenticationException {
			  System.out.println("auth.getName(): "+ auth.getName());  
			  System.out.println("auth.getCredentials(): "+ auth.getCredentials());
			  
		    if ( auth.getName().equals(auth.getPrincipal() )) {
		    	
		    	return new UsernamePasswordAuthenticationToken(auth.getName(),
		        auth.getCredentials(), AUTHORITIES);
		    	
		      }
		    
		      throw new BadCredentialsException("Bad Credentials");
		  }
		
}

