package com.one.viberbot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.one.viberbot.database.entity.Reservation;

public class ReservationServiceImpl {

	@Autowired
	private ReservationService reservationService;
	
	public List<Reservation> getAll() {
		return reservationService.findAll();
	}


	public void reserve(Reservation reservation) {
		reservationService.reserve(reservation);
	}

	public void edit(Reservation reservation) {
		reservationService.edit(reservation);
	}

	public void delete(Integer id) {
		reservationService.delete(id);
	}

//	public int getFreeRoomCapacitiesOnDate(Long roomId, LocalDate date) {
//
//		reservationService.getFreeRoomCapacitiesOnDate(roomId, date);
//		return ;
//	}

	public Reservation getByUser(String viberId) {
		// TODO Auto-generated method stub
		return null;
	}

}
