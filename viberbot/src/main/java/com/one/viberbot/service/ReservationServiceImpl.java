package com.one.viberbot.service;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.one.viberbot.database.entity.Reservation;
import com.one.viberbot.database.repository.ReservationRepository;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;
	
	@Override
	public Iterable<Reservation> getAll() {
		return reservationRepository.findAll();
	}

	@Override
	@Transactional
	public void reserve(Reservation reservation) {
		reservationRepository.save(reservation);
	}

	@Override
	@Transactional
	public void edit(Reservation reservation) {
		reservationRepository.save(reservation);		
	}

	@Override
	@Transactional
	public void delete(Long id) {
		reservationRepository.delete(id);
	}

	@Override
	public Iterable<LocalTime> getFreeRoomCapacitiesOnDate(Long roomId, LocalDate date) {
		return reservationRepository.getFreeRoomCapacitiesOnDate(roomId, date);
	}

	@Override
	public Reservation getByUser(String viberId) {
		return reservationRepository.getByUser(viberId);
	}
	
}
