package com.one.viberbot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.one.viberbot.database.entity.Room;
import com.one.viberbot.database.repository.RoomRepository;

@Service
public class RoomServiceImpl implements RoomService {
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Override
	public Iterable<Room> findAll() {
		return roomRepository.findAll();
	}

	@Override
	public void add(Room room) {
		roomRepository.save(room);
	}

	@Override
	public void update(Room room) {
		roomRepository.save(room);
	}

	@Override
	public Room getOne(Integer id) {
		return roomRepository.findOne(id);
	}

	@Override
	public void delete(Integer id) {
		roomRepository.delete(id);
	}

}
