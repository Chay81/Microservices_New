package com.users_client.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.users_client.database.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
	
//	derived method to find emailID
	UserEntity findByEmail(String email);

}
