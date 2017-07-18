package com.one.viberbot.mvc.controllers.room;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.one.viberbot.service.RoomService;

@Controller
public class Home {
	
	@Autowired
	private RoomService roomService;
	
	@RequestMapping(value = "/rooms", method = RequestMethod.GET)
	public String getAllRooms(Model model) {
		model.addAttribute("rooms", roomService.getAllRooms());
		return "room/rooms";
	}
	
	@RequestMapping(value = "/room/delete/{id}", method = RequestMethod.GET)
	public void deleteRoom(@PathVariable(value = "id") Integer id) {
		roomService.deleteRoom(id);
	}
}
