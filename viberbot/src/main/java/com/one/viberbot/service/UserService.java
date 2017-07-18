package com.one.viberbot.service;

import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;

import com.one.viberbot.database.entity.AppUser;

@Service
public interface UserService extends Repository<AppUser, Integer> {

	void findAll();
	void add(AppUser user);
	AppUser getByViberId(String viberId);
	void subscribe(String viberId);
	void unsubscribe(String viberId);
	void delete(Long id);
	
}
