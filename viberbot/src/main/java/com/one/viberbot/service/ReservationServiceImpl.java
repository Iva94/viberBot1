package com.one.viberbot.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.one.viberbot.database.entity.Reservation;
import com.one.viberbot.database.entity.Room;
import com.one.viberbot.database.repository.ReservationRepository;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired 
	private RoomServiceImpl roomService;
	
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
		List<LocalTime> reservations = (List<LocalTime>) reservationRepository.getFreeRoomCapacitiesOnDate(roomId, date);
		
		//List<LocalTime> reservationTimes = new ArrayList<LocalTime>();
		
		//if(reservations != null)
		//	reservations.forEach(x -> reservationTimes.add(x.getTime()));
		
		List<LocalTime> timeList = new ArrayList<LocalTime>();
		Room room = roomService.getOne(roomId);
		
		Integer startTime = room.getStartWorkTime().getHour();
		Integer endTime = room.getEndWorkTime().getHour();
		LocalTime tmp = LocalTime.MIDNIGHT;
		
		for(int i = startTime; i <= endTime; ++i) {
			LocalTime time = tmp.withHour(i);
			
			if(reservations == null || !reservations.contains(time))
				timeList.add(time);
		}		

		return timeList;
	}

	@Override
	public Iterable<Reservation> getByUser(String viberId) {
		return reservationRepository.getByUser(viberId);
	}



	@Override
	public Reservation findOne(Long id) {
		return reservationRepository.findOne(id);
	}
	
}
