package com.one.viberbot.mvc.controllers.room;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.one.viberbot.database.entity.Room;
import com.one.viberbot.service.RoomService;

@Controller
@RequestMapping(value = "/rooms")
public class RoomsController {
	@Autowired
	private RoomService roomService;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String findAll(Model model) {
		model.addAttribute("rooms", roomService.findAll());
		return "room/rooms";
	}
	
	//@ModelAttribute
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@RequestParam(value = "number") String number,	
					@RequestParam(value = "name") String name,
					@RequestParam(value = "startWorkTime") String startWorkTime,
					@RequestParam(value = "endWorkTime") String endWorkTime, Model model) 
	{	
		LocalTime startTime = LocalTime.parse(startWorkTime, DateTimeFormatter.ISO_LOCAL_TIME);
		LocalTime endTime = LocalTime.parse(endWorkTime, DateTimeFormatter.ISO_LOCAL_TIME);
		
		roomService.add(new Room(number, name, startTime, endTime));
		
		return "redirect:/rooms/all";
	}
	
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addView() {
		return "room/add";
	}
	
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@RequestParam(value = "id") String id,
				@RequestParam(value = "number") String number,	
				@RequestParam(value = "name") String name,
				@RequestParam(value = "startWorkTime") String startWorkTime,
				@RequestParam(value = "endWorkTime") String endWorkTime, Model model) 
	{
		LocalTime startTime = LocalTime.parse(startWorkTime, DateTimeFormatter.ISO_LOCAL_TIME);
		LocalTime endTime = LocalTime.parse(endWorkTime, DateTimeFormatter.ISO_LOCAL_TIME);
		
		roomService.update(new Room(Long.parseLong(id), number, name, startTime, endTime));
				
		return "redirect:/rooms/all";
	}
	
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String updateView(@PathVariable("id") Long id, Model model) {
		model.addAttribute("room", roomService.getOne(id));
		return "room/update";
	}
	
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@RequestParam(value = "id") Long id, Model model) {
		
		roomService.delete(id);
				
		return "redirect:/rooms/all";
	}
}
