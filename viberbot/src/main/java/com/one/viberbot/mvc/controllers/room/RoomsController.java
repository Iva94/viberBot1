package com.one.viberbot.mvc.controllers.room;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.one.viberbot.database.entity.Room;
import com.one.viberbot.service.RoomService;

@Controller
public class RoomsController {
	@Autowired
	private RoomService roomService;
	
	@RequestMapping(value = "/rooms", method = RequestMethod.GET)
	public String findAll(Model model) {
		model.addAttribute("rooms", roomService.findAll());
		return "room/rooms";
	}
	
	@RequestMapping(value = "/rooms/add", method = RequestMethod.POST)
	public void add(@RequestParam(value = "number") String number,	
					@RequestParam(value = "name") String name,
					@RequestParam(value = "startWorkTime") LocalTime startWorkTime,
					@RequestParam(value = "endWorkTime") LocalTime endWorkTime) 
	{
		roomService.add(new Room(number, name, startWorkTime, endWorkTime));
	}
	
	@RequestMapping(value = "/rooms/update", method = RequestMethod.POST)
	public void update(@RequestParam(value = "id") Long id,	
					   @RequestParam(value = "number") String number,	
					   @RequestParam(value = "name") String name,
					   @RequestParam(value = "startWorkTime") LocalTime startWorkTime,
					   @RequestParam(value = "endWorkTime") LocalTime endWorkTime) 
	{
		roomService.update(new Room(id, number, name, startWorkTime, endWorkTime));
	}
	
	@RequestMapping(value = "/rooms/delete", method = RequestMethod.POST)
	public void delete(@RequestParam(value = "id") Integer id) {
		roomService.delete(id);
	}

}
