package com.one.viberbot.mvc.controllers.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.one.viberbot.service.ReservationService;
import com.one.viberbot.service.ReservationServiceImpl;


@Controller
@RequestMapping("/") 



public class ReservationsController {
	
	@Autowired
	private ReservationService reservationService;
	
	@RequestMapping(value = "/reservations", method = RequestMethod.GET)
	public String reservationsView(Model model) {
		model.addAttribute("reservations", reservationService.getAll());
		return "reservations/reservations";
	}

}
