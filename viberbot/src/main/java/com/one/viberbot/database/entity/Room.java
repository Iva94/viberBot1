package com.one.viberbot.database.entity;

import java.io.Serializable;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rooms")
public class Room implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String number;	
	private String name;
	private LocalTime startWorkTime;
	private LocalTime endWorkTime;
	
	public Room() {
		
	}
	
	public Room(Long id, String number, String name, LocalTime startWorkTime, LocalTime endWorkTime) {
		super();
		this.id = id;
		this.number = number;
		this.name = name;
		this.startWorkTime = startWorkTime;
		this.endWorkTime = endWorkTime;
	}

	public Room(String number, String name, LocalTime startWorkTime, LocalTime endWorkTime) {
		super();
		this.number = number;
		this.name = name;
		this.startWorkTime = startWorkTime;
		this.endWorkTime = endWorkTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalTime getStartWorkTime() {
		return startWorkTime;
	}

	public void setStartWorkTime(LocalTime startWorkTime) {
		this.startWorkTime = startWorkTime;
	}

	public LocalTime getEndWorkTime() {
		return endWorkTime;
	}

	public void setEndWorkTime(LocalTime endWorkTime) {
		this.endWorkTime = endWorkTime;
	}
}
