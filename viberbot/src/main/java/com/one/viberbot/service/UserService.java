package com.one.viberbot.service;

import com.one.viberbot.database.entity.AppUser;

public interface UserService {

	Iterable<AppUser> findAll();
	void add(AppUser user);
	AppUser getByViberId(String viberId);
	void subscribe(String viberId);
	void unsubscribe(String viberId);
	void delete(Long id);
	
}
