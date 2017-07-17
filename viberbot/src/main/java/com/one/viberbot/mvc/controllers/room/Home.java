package com.one.viberbot.mvc.controllers.room;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.one.viberbot.service.RoomService;

@RestController
public class Home {
	
	//@Autowired
	//private RoomService roomService;
	
	@RequestMapping("/rooms")
	public String getAllRooms() {
		//return this.roomService.getAllRooms();
		return "All rooms";
	}
}
