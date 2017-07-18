package com.one.viberbot.database.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.one.viberbot.database.entity.Room;

public interface RoomRepository extends Repository<Room, Integer>{
	long count();
	boolean exists(Integer primaryKey);
	public List<Room> findAll();
	Room findOne(Integer id);

	@Transactional
	@Modifying
	@Query("insert into Room (id, number, name, startWorkTime, endWorkTime) values (:id, :number, :name, :startWorkTime, :endWorkTime)")
	public void add(Room room);

	
	public void update(Room room);
	
	@Transactional
	@Modifying
	@Query("delete from Room where id = :id")
	public void delete(Integer id);
}
