package com.users_client.service;

import java.util.ArrayList;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.users_client.DTO.UserDTO;
import com.users_client.database.UserEntity;
import com.users_client.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public UserDTO createUser(UserDTO dto) {
		
//		setting unique userId
		dto.setUserId(UUID.randomUUID().toString());
		
//		encrypting the password
		dto.setEncryptedPassword(passwordEncoder.encode(dto.getPassword()));
		
//		implementing model mapper
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UserEntity userEntity = mapper.map(dto, UserEntity.class);

//		hardcoding encrypted password
//		userEntity.setEncryptedPassword("testing encryptedPassword");
		
		userRepository.save(userEntity);
		
		UserDTO dtoResponse = mapper.map(userEntity, UserDTO.class);
		
		return dtoResponse;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserEntity userEntity = userRepository.findByEmail(username);
		
		if(userEntity == null) throw new UsernameNotFoundException(username);
		
		return new User(userEntity.getEmail(),userEntity.getEncryptedPassword(),
				true,true,true,true, new ArrayList<>());
	}

	@Override
	public UserDTO getUserDetailsByEmail(String email) {
		
		UserEntity userEntity = userRepository.findByEmail(email);
		
		if(userEntity == null) throw new UsernameNotFoundException(email);
		return new ModelMapper().map(userEntity, UserDTO.class);
		
	}

	
	
	
	
}
