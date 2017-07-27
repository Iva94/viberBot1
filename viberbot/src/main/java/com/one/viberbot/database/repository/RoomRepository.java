package com.one.viberbot.database.repository;

import org.springframework.data.repository.CrudRepository;
import com.one.viberbot.database.entity.Room;

public interface RoomRepository extends CrudRepository<Room, Long> {
	
}

