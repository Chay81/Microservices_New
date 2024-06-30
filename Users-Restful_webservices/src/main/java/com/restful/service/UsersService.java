package com.restful.service;

import java.util.List;

import com.restful.model.Users;

public interface UsersService {

	Users createUsers(Users user);
	
	List<Users> getUsers();
	
	Users getUser(String uniqueUserId);
	
	Users updateUser(String uniqueUserId, Users user);
	
	Users deleteUser(String uniqueUserId);
}
