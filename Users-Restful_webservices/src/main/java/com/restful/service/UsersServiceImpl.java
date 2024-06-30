package com.restful.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.restful.model.Users;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UsersServiceImpl implements UsersService {

	private Map<String, Users> users;

//	@Autowired
//	private UsersRepository repository;

	@Override
	public Users createUsers(Users user) {

		log.info("Create User method is called ");

		Users dataUsers = new Users();

		// inserting data into Users objects thru getters and setters
		dataUsers.setName(user.getName());
		dataUsers.setSalary(user.getSalary());
		dataUsers.setEmailAddress(user.getEmailAddress());
		

		// condition to check the object is null
		if (users == null) {
			users = new HashMap<>();

			// creating unique key
			String userId = UUID.randomUUID().toString();
			dataUsers.setUniqueUserId(userId);

			users.put(userId, dataUsers);

		}
		return dataUsers;
	}

	@Override
	public List<Users> getUsers() {

		log.info("Get users method is called ");

		return new ArrayList<>(users.values());
//		return repository.findAll(); // using repo method 
	}

	@Override
	public Users getUser(String uniqueUserId) {

		log.info("Get User method is called ");

		Optional<Users> getUserByOptional = Optional.of(users.get(uniqueUserId));

		if (getUserByOptional.isPresent()) {

			log.info("User with Id is : " + uniqueUserId);

		}else {
			log.info("No User Found");
		}

		return getUserByOptional.get();

	}

	@Override
	public Users updateUser(String uniqueUserId, Users user) {

		log.info("Update user method called ");

		Users updateUser = users.get(uniqueUserId);
//		if (repository.existsById(id)) {
//			repository.save(user);
//		} else {
//			log.info("No user found ");
//		}
//		return updateUser;

//		Since jpa repository is not uaed hence using map methods to update the data
		updateUser.setName(user.getName());
		updateUser.setSalary(user.getSalary());
		updateUser.setEmailAddress(user.getEmailAddress());

//		updating the map 
		users.put(uniqueUserId, updateUser);

		return updateUser;

	}

	@Override
	public Users deleteUser(String uniqueUserId) {

		log.info("Delete user method called ");

		Users dUser = new Users();
//		if (users.containsKey(uniqueUserId) {
//			repository.deleteById(uniqueUserId);
//		} else {
//			log.info("No User found");
//		}

		return users.remove("User " + dUser + " has been deleted");

	}

}
