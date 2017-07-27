package com.one.viberbot.database.repository;

import org.springframework.data.repository.CrudRepository;
import com.one.viberbot.database.entity.AppUser;

public interface UserRepository extends CrudRepository<AppUser, Long>{
	
	public AppUser getByViberId(String viberId);
	
}
