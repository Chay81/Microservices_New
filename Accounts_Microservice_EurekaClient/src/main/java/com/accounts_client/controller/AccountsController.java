package com.accounts_client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/accounts")
public class AccountsController {
	
	@GetMapping(value = "/status")
	public String status() {
		return "Working";
	}

}
