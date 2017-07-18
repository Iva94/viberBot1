package com.one.viberbot.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.one.viberbot.database.entity.AppUser;

public class UserServiceImpl implements UserService {

	@Autowired
	public UserService userService;
	
	public void findAll() {
		userService.findAll();
	}

	public void add(AppUser user) {
		userService.add(user);
	}

	public AppUser getByViberId(String viberId) {
		AppUser user;
		user = userService.getByViberId(viberId);
		return user;
	}

	public void subscribe(String viberId) {
		userService.subscribe(viberId);
	}

	public void unsubscribe(String viberId) {
		userService.unsubscribe(viberId);
	}

	public void delete(Long id) {
		userService.delete(id);
	}
}
