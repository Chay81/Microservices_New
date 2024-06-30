package com.users_client.DTO;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6725300995502914543L;
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String userId;
	private String encryptedPassword;

}
