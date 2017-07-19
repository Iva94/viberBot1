package com.one.viberbot.database.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.one.viberbot.database.entity.AppUser;

public interface UserRepository extends CrudRepository<AppUser, Long>{
	
	@Query("select u from AppUser u where u.viberId = :viberId")
	public AppUser getByViberId(String viberId);
	
}
