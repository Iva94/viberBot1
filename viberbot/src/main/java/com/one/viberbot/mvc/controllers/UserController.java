package com.one.viberbot.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.one.viberbot.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
		
	@RequestMapping(value = "/users")
	public String viewUsers(Model model) {
		model.addAttribute("users", userService.findAll());
		return "users/users";
	}
}
