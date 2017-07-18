package com.one.viberbot.service;

import java.util.List;
import org.springframework.data.repository.Repository;
import com.one.viberbot.database.entity.Reservation;


public interface ReservationService extends Repository<Reservation, Integer>{
	
	
	List<Reservation> findAll();
	void reserve(Reservation reservation);
	void edit(Reservation reservation);
	void delete(Long id);
	//List<Time> getFreeRoomCapacitiesOnDate(Integer roomId, LocalDate date);
	Reservation getByUser(String viberId);
	
	
}
