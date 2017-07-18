package com.one.viberbot.database.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Integer id;
	
	
	private String viberId;
	
	
	private String name;
	
	private Boolean subscribe;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
	
	public User(){
		
	}
	
	public User(Integer id, String viberId, String name, Boolean subscribe){
		super();
		this.id = id;
		this.viberId = viberId;
		this.name = name;
		this.subscribe = subscribe;
	}

}
