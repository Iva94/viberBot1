package com.one.viberbot.service;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.one.viberbot.database.entity.Reservation;


public interface ReservationService extends Repository<Reservation, Integer>{

	List<Reservation> findAll();
	
	@Transactional
	@Modifying
	@Query("insert into Reservation (id, appuser, room, date, time) values (:id, :appuser, :room, :date, :time)")
	void reserve(Reservation reservation);
	
	
	void edit(Reservation reservation);
	
	@Transactional
	@Modifying
	@Query("delete from Reservation where id = :id")
	void delete(@Param("id")Integer id);
	
	//List<Time> getFreeRoomCapacitiesOnDate(Integer roomId, LocalDate date);
	
	@Transactional
	@Modifying
	@Query("select r from Reservation r where r.appuser = :viberId")
	Reservation getByUser(@Param("viberId")String viberId);
	
	
}
