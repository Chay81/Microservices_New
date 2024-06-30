package com.restful.model;

import com.restful.custom_annotations.ValidEmailDomain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

	private String uniqueUserId;

	@NotNull(message = "name cannot be blank")
	@Size(min = 4, max = 20, message = "Name should have more than 4 characters")
	private String name;

	@NotNull(message = "Salary cannot be blank and cannot be less than 20000 and more than 200000")
	@Min(20000)
	@Max(200000)
	private int salary;

	@NotNull
	@Email(message = "Invalid email format")
	@ValidEmailDomain(allowedDomains = { "gmail.com", "yahoo.com", "hotmail.com" })
	private String emailAddress;

}
