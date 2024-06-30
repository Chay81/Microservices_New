package com.accounts_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AccountsMicroserviceEurekaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsMicroserviceEurekaClientApplication.class, args);
	}

}
