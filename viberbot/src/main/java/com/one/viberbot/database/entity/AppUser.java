package com.one.viberbot.database.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "appuser")
public class AppUser implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="viber_id")
	private String viberId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="subscribe")
	private Boolean subscribe;

	public AppUser() {
		
	}
	
	public AppUser(Long id, String viberId, String name, Boolean subscribe){
		this.id = id;
		this.viberId = viberId;
		this.name = name;
		this.subscribe = subscribe;
	}
	
	public AppUser(String viberId, String name, Boolean subscribe){
		this.viberId = viberId;
		this.name = name;
		this.subscribe = subscribe;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getViberId() {
		return viberId;
	}

	public void setViberId(String viberId) {
		this.viberId = viberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(Boolean subscribe) {
		this.subscribe = subscribe;
	}

}
