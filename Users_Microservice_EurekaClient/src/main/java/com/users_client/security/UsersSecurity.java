package com.users_client.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.users_client.service.UserService;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class UsersSecurity {

//	configuration for environment variables
	@Autowired
	private Environment environment;
	
	@Autowired
	private UserService service;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private BCryptPasswordEncoder cryptPasswordEncoder;
	
	@Bean 
	protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {

//		code for authentication manager
		AuthenticationManagerBuilder builder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
		
//		code for username and password authentication
		builder.userDetailsService(service).passwordEncoder(cryptPasswordEncoder);
		
		AuthenticationManager authManager = builder.build(); 
		
		httpSecurity.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests
//					(authorizeRequests -> authorizeRequests.requestMatchers(HttpMethod.POST, "/users").permitAll()
				(authorizeRequests -> 
					authorizeRequests.requestMatchers(HttpMethod.POST, "/users")
						.access(new WebExpressionAuthorizationManager("hasIpAddress('"+environment.getProperty("gateway.ip")+"')")) // environment configuration
				.requestMatchers(new AntPathRequestMatcher("/**")).permitAll()
				.anyRequest()
				.authenticated())
				.addFilter(new AuthenticationFilter(authManager,service, env)) // code for authManager
				.authenticationManager(authManager) // code for authManager
				.sessionManagement(
						sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.headers
					(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));

		return httpSecurity.build();
	}

}
