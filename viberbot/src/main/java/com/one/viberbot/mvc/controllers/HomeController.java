package com.one.viberbot.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {	
	@RequestMapping(value = "/login")
	public String login() {
		return "/login";
	}
}
