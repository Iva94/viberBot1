package com.one.viberbot.database.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reservation")
public class Reservation implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "appuser")
	private AppUser appuser;
	
	@ManyToOne
	@JoinColumn(name = "room")
	private Room room;
	
	@Column(name="date")
	private LocalDate date;
	
	@Column(name="time")
	private LocalTime time;
	
	public Reservation() {
		
	}

	public Reservation(Long id, AppUser user, Room room, LocalDate date, LocalTime time){
		this.id = id;
		this.appuser = user;
		this.room = room;
		this.date = date;
		this.time = time;
	}
	
	public Reservation(AppUser user, Room room, LocalDate date, LocalTime time){
		this.appuser = user;
		this.room = room;
		this.date = date;
		this.time = time;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AppUser getAppuser() {
		return appuser;
	}

	public void setAppuser(AppUser appuser) {
		this.appuser = appuser;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

}
