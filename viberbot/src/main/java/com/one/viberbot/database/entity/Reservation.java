package com.one.viberbot.database.entity;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

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
	private Integer id;
	
	@ManyToOne(targetEntity = AppUser.class)
	@JoinColumn(name = "appuser", referencedColumnName = "id")
	private AppUser appuser;
	
	@ManyToOne(targetEntity = Room.class)
	@JoinColumn(name = "room", referencedColumnName = "id")
	private Room room;
	
	private Date date;
	private Time time;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AppUser getUser() {
		return appuser;
	}

	public void setUser(AppUser user) {
		this.appuser = user;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public Reservation(){
		
	}

	public Reservation(Integer id, AppUser user, Room room, Date date, Time time){
		this.id = id;
		this.appuser = user;
		this.room = room;
		this.date = date;
		this.time = time;
	}
}
