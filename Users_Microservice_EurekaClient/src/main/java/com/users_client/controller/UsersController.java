package com.users_client.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.users_client.DTO.UserDTO;
import com.users_client.DTO.UserDetailsResponse;
import com.users_client.model.UserDetails;
import com.users_client.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UsersController {

	@Autowired
	private UserService userService;

	@GetMapping(value = "/status")
	public String status() {
		return "Working";
	}

	@PostMapping
	public ResponseEntity<UserDetailsResponse> createUser(@Valid @RequestBody UserDetails details) {

//		 implementing model mapper
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UserDTO userDTO = mapper.map(details, UserDTO.class);
		
//		implementing userdetailsResponse
		UserDTO createdUser = userService.createUser(userDTO);
		UserDetailsResponse response = mapper.map(createdUser, UserDetailsResponse.class);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

}
