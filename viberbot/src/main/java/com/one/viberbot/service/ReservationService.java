package com.one.viberbot.service;

import java.time.LocalDate;
import java.time.LocalTime;

import com.one.viberbot.database.entity.Reservation;

public interface ReservationService {

	Iterable<Reservation> getAll();
	void reserve(Reservation reservation);
	void edit(Reservation reservation);
	void delete(Long id);
	Iterable<LocalTime> getFreeRoomCapacitiesOnDate(Long roomId, LocalDate date);
	Reservation getByUser(String viberId);
	
}
