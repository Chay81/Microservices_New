package com.users_client.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.users_client.DTO.UserDTO;

// Note: UserDetailsService is extended due to UserSecurity class, method userDetailsService is a import of spring security
public interface UserService extends UserDetailsService{

	UserDTO createUser(UserDTO dto);
	
//	this method is needed to get user details
	UserDTO getUserDetailsByEmail(String email);
	
}
