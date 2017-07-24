package com.one.viberbot.mvc.controllers.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.one.viberbot.service.ReservationService;

@Controller
public class ReservationController {
	@Autowired
	private ReservationService reservationService;
		
	@RequestMapping(value = "/reservations")
	public String viewReservations(Model model) {
		model.addAttribute("reservations", reservationService.getAll());
		return "reservations/reservations";
	}
}
