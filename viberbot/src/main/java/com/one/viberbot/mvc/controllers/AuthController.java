package com.one.viberbot.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


public class AuthController {
    
	@RequestMapping(value = "/authuser", method = RequestMethod.POST)
	public String authUserLogin(@RequestParam("email") String email, 
			@RequestParam("password") String password) {
		
		if(email == "email@example.com" && password == "admin123")
			return "/administration/dashboard";
		
		return "/error";
	}
    
}
