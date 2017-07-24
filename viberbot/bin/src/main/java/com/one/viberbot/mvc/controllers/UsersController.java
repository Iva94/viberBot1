package com.one.viberbot.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.one.viberbot.service.ReservationService;
import com.one.viberbot.service.UserService;

@Controller
@RequestMapping("/")

public class UsersController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String usersView(Model model) {
		model.addAttribute("user", userService.findAll());
		return "users/users";
	}

}
