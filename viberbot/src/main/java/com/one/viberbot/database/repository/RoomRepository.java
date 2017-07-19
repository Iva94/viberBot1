package com.one.viberbot.database.repository;

import org.springframework.data.repository.CrudRepository;
import com.one.viberbot.database.entity.Room;

public interface RoomRepository extends CrudRepository<Room, Long> {
	
}

/*
public interface RoomRepository extends Repository<Room, Integer>{
	long count();
	boolean exists(Integer primaryKey);
	public List<Room> findAll();
	Room findOne(Integer id);

	@Transactional
	@Modifying
	@Query("insert into Room (number, name, startWorkTime, endWorkTime) values (:number, :name, :startWorkTime, :endWorkTime)")
	public void add(@Param(value = "number") String number,	
					@Param(value = "name") String name,
					@Param(value = "startWorkTime") LocalTime startWorkTime,
					@Param(value = "endWorkTime") LocalTime endWorkTime);

	@Transactional
	@Modifying
	@Query("alter table Room (number, name, startWorkTime, endWorkTime) values (:number, :name, :startWorkTime, :endWorkTime)")
	public void update(Room room);
	
	@Transactional
	@Modifying
	@Query("delete from Room where id = :id")
	public void delete(Integer id);
	
}
*/

