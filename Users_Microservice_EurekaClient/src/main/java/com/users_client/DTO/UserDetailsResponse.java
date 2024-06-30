package com.users_client.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsResponse {

	@NotNull(message="FirstName cannot be empty")
	@Size(min=5, message="First name cannot be less than 5 characters")
	private String firstName;
	
	@NotNull(message="LastName cannot be empty")
	@Size(min=3, message="Lasst name cannot be less than 3 characters")
	private String lastName;
	
	@NotNull(message="Email Address cannot be empty")
	@Email
	private String email;
	
	@NotNull(message="Password cannot be empty")
	@Size(min=5, max = 14, message = "password cannot be less than 5 characters and more than 14 characters")
	private String userId;
}
