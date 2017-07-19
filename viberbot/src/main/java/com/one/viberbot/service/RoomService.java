package com.one.viberbot.service;

import com.one.viberbot.database.entity.Room;

public interface RoomService {
	
	public Iterable<Room> findAll();
	public void add(Room room);
	public void update(Room room);
	public Room getOne(Long id);
	public void delete(Long id);
	
}
