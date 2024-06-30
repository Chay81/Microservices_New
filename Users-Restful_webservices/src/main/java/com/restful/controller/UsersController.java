package com.restful.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restful.model.Users;
import com.restful.service.UsersService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/users")
@Slf4j
@RequiredArgsConstructor
public class UsersController {

	@Autowired
	private UsersService service;

	@PostMapping(value = "/createUser", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Users> createUsers(@Valid @RequestBody Users users) {

		log.info("UsersController | createUser is called");

		Users cUser = service.createUsers(users);
		return new ResponseEntity<>(cUser, HttpStatus.CREATED);
	}

	@GetMapping(value = "/getUsers", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Users>> getUsers() {

		log.info("UsersController | GetUsers is called");

		List<Users> cUser = service.getUsers();

		if (cUser != null) {
			return new ResponseEntity<>(cUser, HttpStatus.ACCEPTED);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(path = "/{uniqueUserId}")
	public ResponseEntity<Users> getUser(@PathVariable String uniqueUserId) {

		log.info("UsersController | GetUser is called");
		
		Users cUser = service.getUser(uniqueUserId);

		if (uniqueUserId != null) {
			return new ResponseEntity<>(cUser, HttpStatus.OK);
		} else {
			return ResponseEntity.notFound().build();
			
		}
	}

	@PutMapping(path = "/{uniqueUserId}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Users> updateUsers(@PathVariable String uniqueUserId, @Valid @RequestBody Users users) {

		log.info("UsersController | UpdateUser is called");

		Users cUser = service.updateUser(uniqueUserId, users);
		return new ResponseEntity<>(cUser, HttpStatus.OK);
	}

	@DeleteMapping(path = "/{uniqueUserId}")
	public ResponseEntity<Users> deleteUser(@PathVariable String uniqueUserId) {

		log.info("UsersController | Delete User is called");

		Users cUser = service.deleteUser(uniqueUserId);

		return new ResponseEntity<>(cUser, HttpStatus.NO_CONTENT);
	}
}
