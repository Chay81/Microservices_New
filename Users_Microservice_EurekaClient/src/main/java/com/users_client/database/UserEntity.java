package com.users_client.database;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class UserEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7487198934927788765L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long databaseID;
	
	@Column(nullable=false, length=50)
	private String firstName;
	
	@Column(nullable=false, length=50)
	private String lastName;
	
	@Column(nullable=false, length=120, unique = true)
	private String email;
	
	@Column(nullable=false, unique = true)
	private String userId;
	
	@Column(nullable=false, unique = true)
	private String encryptedPassword;
}
