package com.one.viberbot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.one.viberbot.database.entity.Room;
import com.one.viberbot.database.repository.RoomRepository;

@Service
public class RoomService {
	
	@Autowired
	private RoomRepository roomRepository;
	
	public List<Room> getAllRooms() {
		return roomRepository.findAll();
	}
	
}
