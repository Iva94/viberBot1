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
	
	public void addRoom(Room room) {
		roomRepository.add(room);
	}
	
	public void updateRoom(Room room) {
		roomRepository.update(room);
	}
	
	public Room getOneRoom(Integer id) {
	   return roomRepository.findOne(id);
	}
	
	public void deleteRoom(Integer id) {
		   roomRepository.delete(id);
		}
}
