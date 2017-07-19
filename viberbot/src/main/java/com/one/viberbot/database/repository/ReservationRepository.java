package com.one.viberbot.database.repository;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.one.viberbot.database.entity.Reservation;


public interface ReservationRepository extends CrudRepository<Reservation, Long> {
	
	@Query("select time from Reservation where room = :roomId and date = :date")
	public Iterable<LocalTime> getFreeRoomCapacitiesOnDate(@Param("roomId") Long roomId, @Param("date") LocalDate date);
	
	@Query("select r from Reservation r where r.appuser = (select u.id from AppUser u where u.viberId = :viberId)")
	public Reservation getByUser(@Param("viberId") String viberId);
	
}
