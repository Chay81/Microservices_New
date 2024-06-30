package com.users_client.security;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.users_client.DTO.UserDTO;
import com.users_client.model.LoginRequest;
import com.users_client.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private Environment env;

	public AuthenticationFilter(AuthenticationManager authManager,
						UserService userService,
						Environment env) {
		super(authManager);
		this.env = env;
		this.userService = userService;
		
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {

		try {

			LoginRequest credentials = new ObjectMapper().readValue(req.getInputStream(), LoginRequest.class);

			return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
					credentials.getEmail(), credentials.getPassword(), new ArrayList<>()));

		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}

	}
	

	protected void successfulAuthetication(HttpServletRequest req, HttpServletResponse res, 
			FilterChain chain, Authentication auth) throws IOException, ServletException{
		
		String userName = ((User)auth.getPrincipal()).getUsername();
		UserDTO userDTODetails = userService.getUserDetailsByEmail(userName);
		
		String tokenSecret = env.getProperty("token.secret");
		byte[] secretKeyBytes = Base64.getEncoder().encode(tokenSecret.getBytes());
		
		SecretKey secretKey = Keys.hmacShaKeyFor(secretKeyBytes);
		
		String token = Jwts.builder()
						.subject(userDTODetails.getUserId())
						.expiration(Date.from(Instant.now()
								.plusMillis(Long.parseLong(env.getProperty("token.expiration_time")))))
						.issuedAt(Date.from(Instant.now()))
						.signWith(secretKey, Jwts.SIG.HS512)
						.compact();
		
		res.addHeader("token", token);
		res.addHeader("userId", userDTODetails.getUserId());;
		
	}

}
