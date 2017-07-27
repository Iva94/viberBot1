package com.one.viberbot.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.one.viberbot.database.entity.AppUser;
import com.one.viberbot.database.entity.Reservation;
import com.one.viberbot.database.entity.Room;
import com.viber.bot.event.incoming.IncomingConversationStartedEvent;
import com.viber.bot.message.Message;
import com.viber.bot.message.MessageKeyboard;
import com.viber.bot.message.TextMessage;
import com.viber.bot.message.TrackingData;

@Service
public class ConversationService {
	
	@Autowired 
	private ReservationService reservationService;
	
	@Autowired 
	private RoomServiceImpl roomService;
		
	@Autowired
	private UserServiceImpl userService;
	
	public TextMessage newMessage(TrackingData trackingData, Message message) {
		TextMessage newTextMessage = new TextMessage("An error occurred1, please try again later.");
		String sentMessage = message.getMapRepresentation().get("text").toString();
		
		try{		
			if(sentMessage.equalsIgnoreCase("Home"))
				newTextMessage = initMessage(trackingData);
			else if(sentMessage.equalsIgnoreCase("Back"))
		    	newTextMessage=goingBack(sentMessage, trackingData);
			else
			switch(trackingData.get("step").toString().toLowerCase()) {
				case "init":
					if(sentMessage.equalsIgnoreCase("make a reservation"))
						newTextMessage = makeReservation(trackingData);
					else if(sentMessage.equalsIgnoreCase("view all reservations"))
						newTextMessage = viewReservations(trackingData);
					else 
						newTextMessage = initMessage(trackingData);
					break;
				case "enter date":
					newTextMessage = chooseReservationDate(sentMessage, trackingData);
					break;
				case "select room for date":
					newTextMessage = selectRoom(trackingData, sentMessage);
					break;
				case "select reservation time":
					newTextMessage = selectTime(trackingData, sentMessage);
					break;
				case "confirm reservation":
					newTextMessage = confirmReservation(trackingData, sentMessage);
					break;
				case "showed reservations":
					newTextMessage = showReservation(trackingData, sentMessage);
					break;
				case "canceled reservation":
					newTextMessage = confirmCancellation(trackingData, sentMessage);
					break;
				case "canceled":
					newTextMessage = cancellation(trackingData, sentMessage);
					break;
			}
		}catch(Exception e){
			Map<String, Object> trackingMap = new HashMap<String, Object>();
			trackingMap.put("user-id", trackingData.get("user-id").toString());
			trackingMap.put("user-name", trackingData.get("user-name").toString());
						
			TrackingData newTrackingData = new TrackingData(trackingMap);
			
			List<Map<String, Object>> buttons = new ArrayList<Map<String, Object>>();
			buttons.add(makeButtonWhiteText(6, 1, "#675ca8", "reply", "Home", "Home"));
			
			MessageKeyboard keyboard = makeKeyboard(buttons);
			
			newTextMessage = new TextMessage(e.getMessage(), keyboard, newTrackingData, null);
		}
		
		return newTextMessage;
	}
	
	
	public TextMessage goingBack(String sentMessage, TrackingData trackingData) throws Exception {
		
		if(trackingData.get("step").toString().equalsIgnoreCase("select reservation time"))
			return selectRoom(trackingData, sentMessage);
		
		else if(trackingData.get("step").toString().equalsIgnoreCase("select room for date"))
			return makeReservation(trackingData);
		
		else if(trackingData.get("step").toString().equalsIgnoreCase("canceled reservation"))
			 return viewReservations(trackingData);
		
		else if(trackingData.get("step").toString().equalsIgnoreCase("confirm reservation"))
			 return selectTime(trackingData, sentMessage);
		
		return new TextMessage("Error");
	}


	public TextMessage initMessage(TrackingData trackingData) throws Exception {
		
		List<Map<String, Object>> buttons = new ArrayList<Map<String, Object>>();
		
		buttons.add(makeButton(3, 2, "#f6f7f9", "reply", "Make a reservation", "Make a reservation"));
		buttons.add(makeButton(3, 2, "#f6f7f9", "reply", "View all reservations", "View all reservations"));

		MessageKeyboard keyboard = makeKeyboard(buttons);
		
		Map<String, Object> trackingMap = new HashMap<String, Object>();
		trackingMap.put("user-id", trackingData.get("user-id").toString());
		trackingMap.put("user-name", trackingData.get("user-name").toString());
		trackingMap.put("step", "init");
		
		TrackingData newTrackingData = new TrackingData(trackingMap);
		
		return new TextMessage("Hello " + trackingData.get("user-name").toString() + "!", keyboard, newTrackingData, null);
	}
	
	public TextMessage conversationStartedMessage(IncomingConversationStartedEvent event) {
		TextMessage message = new TextMessage("We are sorry. An error occurred, please try again later.");
		
		Map<String, Object> trackingMap = new HashMap<String, Object>();
		
		trackingMap.put("user-id", event.getUser().getId().toString());
		trackingMap.put("user-name", event.getUser().getName().toString());
		TrackingData newTrackingData = new TrackingData(trackingMap);
		
		try{
			message = initMessage(newTrackingData);
		}catch(Exception e){
		
			List<Map<String, Object>> buttons = new ArrayList<Map<String, Object>>();
			buttons.add(makeButtonWhiteText(6, 1, "#675ca8", "reply", "Home", "Home"));
			
			MessageKeyboard keyboard = makeKeyboard(buttons);
			
			message = new TextMessage(e.getMessage(), keyboard, newTrackingData, null);
		}
		
		return message;
	}
	
	public TextMessage makeReservation (TrackingData trackingData) throws Exception {
		
		List<Map<String, Object>> buttons = new ArrayList<Map<String, Object>>();
		buttons.add(makeButtonWhiteText(6, 1, "#675ca8", "reply", "Home", "Home"));
		
		MessageKeyboard keyboard = makeKeyboard(buttons);
		
		Map<String, Object> trackingMap = new HashMap<String, Object>();
		trackingMap.put("user-id", trackingData.get("user-id").toString());
		trackingMap.put("user-name", trackingData.get("user-name").toString());
		trackingMap.put("step", "enter date");
		
		TrackingData newTrackingData = new TrackingData(trackingMap);

		return new TextMessage("Please enter a date for a reservation in format: 'dd.mm.yyyy')", keyboard, newTrackingData, null);
	}
	
	public TextMessage chooseReservationDate(String sentMessage, TrackingData trackingData) throws Exception {
		LocalDate inputDate;
		
		try {
			inputDate = LocalDate.parse(sentMessage, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
		}
		catch(Exception e){
			return makeReservation(trackingData);
		}
		
		if(validateReservationDate(inputDate)) {
			List<Map<String, Object>> buttons = new ArrayList<Map<String, Object>>();
			List<Room> rooms = (List<Room>) roomService.findAll();
				
			if(!rooms.isEmpty())
				for(Room r : rooms)
					buttons.add(makeButton(3, 1, "#f6f7f9", "reply", r.getId().toString(), r.getName()));
			else
				buttons.add(makeButton(6, 2, "#f6f7f9", "reply", "No rooms for reservation", "No rooms for reservation"));
			
			buttons.add(makeButtonWhiteText(6, 1, "#675ca8", "reply", "Home", "Home"));
			buttons.add(makeButtonWhiteText(6, 1, "#675ca8", "reply", "Back", "Back"));
			
			
			MessageKeyboard keyboard = makeKeyboard(buttons);
			
			Map<String, Object> trackingMap = new HashMap<String, Object>();
			trackingMap.put("user-id", trackingData.get("user-id").toString());
			trackingMap.put("user-name", trackingData.get("user-name").toString());
			trackingMap.put("reservation-date", sentMessage);
			trackingMap.put("step", "select room for date");
			
			TrackingData newTrackingData = new TrackingData(trackingMap);
			
			return new TextMessage("Please select a room for reservation.", keyboard, newTrackingData, null);
		}
		
		return makeReservation(trackingData);
	}
	
	public TextMessage selectRoom(TrackingData trackingData, String sentMessage) throws Exception {
		
		if(sentMessage.matches("^[0-9]*$")){
			List<Map<String, Object>> buttons = new ArrayList<Map<String, Object>>();
			
			LocalDate date = LocalDate.parse(trackingData.get("reservation-date").toString(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
			List<LocalTime> freeRoomCapacitiesOnDate = (List<LocalTime>) reservationService.getFreeRoomCapacitiesOnDate(Long.parseLong(sentMessage), date);
			
			for(LocalTime t : freeRoomCapacitiesOnDate)
				buttons.add(makeButton(3, 1, "#f6f7f9", "reply", t.toString(), t.toString() + "h"));
			
			buttons.add(makeButtonWhiteText(6, 1, "#675ca8", "reply", "Home", "Home"));
			buttons.add(makeButtonWhiteText(6, 1, "#675ca8", "reply", "Back", "Back"));
			
			MessageKeyboard keyboard = makeKeyboard(buttons);
			
			Map<String, Object> trackingMap = new HashMap<String, Object>();
			trackingMap.put("user-id", trackingData.get("user-id").toString());
			trackingMap.put("user-name", trackingData.get("user-name").toString());
			trackingMap.put("reservation-date", trackingData.get("reservation-date").toString());
			trackingMap.put("reservation-room-id", sentMessage);
			trackingMap.put("step", "select reservation time");
			
			TrackingData newTrackingData = new TrackingData(trackingMap);
			
			return new TextMessage("Please select a time for reservation.", keyboard, newTrackingData, null);
		}
		
		return chooseReservationDate(trackingData.get("reservation-date").toString(), trackingData);
	}
	
	public TextMessage selectTime(TrackingData trackingData, String sentMessage) throws Exception {
		
		if(sentMessage.matches("^[0-9]{2}:[0-9]{2}$")){

			List<Map<String, Object>> buttons = new ArrayList<Map<String, Object>>();
			buttons.add(makeButton(3, 1, "#f6f7f9", "reply", "Confirm",  "Confirm"));
			buttons.add(makeButton(3, 1, "#f6f7f9", "reply", "Cancel", "Cancel"));
			buttons.add(makeButtonWhiteText(6, 1, "#675ca8", "reply", "Home", "Home"));
			buttons.add(makeButtonWhiteText(6, 1, "#675ca8", "reply", "Back", "Back"));
			
			MessageKeyboard keyboard = makeKeyboard(buttons);
			
			Map<String, Object> trackingMap = new HashMap<String, Object>();
			trackingMap.put("user-id", trackingData.get("user-id").toString());
			trackingMap.put("user-name", trackingData.get("user-name").toString());
			trackingMap.put("reservation-date", trackingData.get("reservation-date").toString());
			trackingMap.put("reservation-room-id", trackingData.get("reservation-room-id").toString());
			trackingMap.put("reservation-time", sentMessage);
			trackingMap.put("step", "confirm reservation");
			
			TrackingData newTrackingData = new TrackingData(trackingMap);
			
			Long roomId = Long.parseLong(newTrackingData.get("reservation-room-id").toString());
			Room room = roomService.getOne(roomId);
			
			return new TextMessage("Please confirm reservation: \nRoom: " + room.getName() + " \nDate: " + newTrackingData.get("reservation-date") + ". \nTime: " + newTrackingData.get("reservation-time") + "h", keyboard, newTrackingData, null);
		}
		
		return selectRoom(trackingData, trackingData.get("reservation-room-id").toString());
	}
	
	public TextMessage confirmReservation(TrackingData trackingData, String sentMessage) throws Exception {
		
		Map<String, Object> trackingMap = new HashMap<String, Object>();
		trackingMap.put("user-id", trackingData.get("user-id").toString());
		trackingMap.put("user-name", trackingData.get("user-name").toString());
	
					
		TrackingData newTrackingData = new TrackingData(trackingMap);
		
		List<Map<String, Object>> buttons = new ArrayList<Map<String, Object>>();
		buttons.add(makeButtonWhiteText(6, 1, "#675ca8", "reply", "Home", "Home"));
		
		
		
		MessageKeyboard keyboard = makeKeyboard(buttons);
		
		if(sentMessage.equalsIgnoreCase("confirm")){
						
			AppUser user = userService.getByViberId(trackingData.get("user-id").toString());
			Room room = roomService.getOne(Long.parseLong(trackingData.get("reservation-room-id").toString()));
			LocalDate date = LocalDate.parse(trackingData.get("reservation-date").toString(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
			LocalTime time = LocalTime.parse(trackingData.get("reservation-time").toString(), DateTimeFormatter.ISO_LOCAL_TIME);
							
			if(user != null && room != null && date != null && time != null){
				reservationService.reserve(new Reservation(user, room, date, time));
								
				return new TextMessage("Successful reservation!", keyboard, newTrackingData, null);	
			}
			
			return new TextMessage("Reservation not successful", keyboard, newTrackingData, null);
		}
		else if(sentMessage.equalsIgnoreCase("cancel"))
			return new TextMessage("Reservation canceled", keyboard, newTrackingData, null);
		
		
		return selectTime(trackingData, trackingData.get("reservation-time").toString());
	}
	
	
	public TextMessage viewReservations(TrackingData trackingData) throws Exception {
			
		List<Map<String, Object>> buttons = new ArrayList<Map<String, Object>>();
		List<Reservation> reservations = (List<Reservation>) reservationService.getByUser(trackingData.get("user-id").toString());
		
		reservations.sort((x,y) -> x.getDate().compareTo(y.getDate()));
		reservations.sort((x,y) -> x.getTime().compareTo(y.getTime()));
		
		for(Reservation r : reservations)					
			buttons.add(makeButton(3, 1, "#f6f7f9", "reply", r.getId().toString(), r.getRoom().getName().toString() + "  -  "  + r.getDate().toString() + "\n" + r.getTime().toString() + "h"));
		 
		buttons.add(makeButtonWhiteText(6, 1, "#675ca8", "reply", "Home", "Home"));
		
		MessageKeyboard keyboard = makeKeyboard(buttons);
		
		Map<String, Object> trackingMap = new HashMap<String, Object>();
		trackingMap.put("user-id", trackingData.get("user-id").toString());
		trackingMap.put("user-name", trackingData.get("user-name").toString());
		trackingMap.put("step", "showed reservations");
		
		TrackingData newTrackingData = new TrackingData(trackingMap);
		
		if(reservations.isEmpty())
		{ 
			return new TextMessage("There is no reservations.", keyboard, newTrackingData, null);
		}
		
		return new TextMessage("Choose reservation", keyboard, newTrackingData, null);
	}

	public TextMessage showReservation(TrackingData trackingData, String sentMessage) throws Exception {
	
		if(sentMessage.matches("^[0-9]*$")){
			Reservation reservation = reservationService.findOne(Long.parseLong(sentMessage));
			
			List<Map<String, Object>> buttons = new ArrayList<Map<String, Object>>();
			buttons.add(makeButton(6, 1, "#f6f7f9", "reply", "Cancel reservation", "Cancel reservation"));
			buttons.add(makeButtonWhiteText(6, 1, "#675ca8", "reply", "Home", "Home"));
			buttons.add(makeButtonWhiteText(6, 1, "#675ca8", "reply", "Back", "Back"));
				
			MessageKeyboard keyboard = makeKeyboard(buttons);
			
			Map<String, Object> trackingMap = new HashMap<String, Object>();
			trackingMap.put("user-id", trackingData.get("user-id").toString());
			trackingMap.put("reservation-id", sentMessage);
			trackingMap.put("user-name", trackingData.get("user-name").toString());
			trackingMap.put("step", "canceled reservation");

			TrackingData newTrackingData = new TrackingData(trackingMap);
			
			return new TextMessage("Reservation details:\nRoom name: "+ reservation.getRoom().getName() + "\nDate: " + reservation.getDate() + "\nTime: " + reservation.getTime() + "h", keyboard, newTrackingData, null);		
		}
		else
			return viewReservations(trackingData);
	}

	public TextMessage confirmCancellation(TrackingData trackingData, String sentMessage) throws Exception {
		
		if(sentMessage.equalsIgnoreCase("cancel reservation")){
			List<Map<String, Object>> buttons = new ArrayList<Map<String, Object>>();
			
			buttons.add(makeButton(6, 1, "#f6f7f9", "reply", "Cancel reservation", "Yes, cancel reservation"));
			buttons.add( makeButtonWhiteText(6, 1, "#675ca8", "reply", "Home", "Home"));
						
			MessageKeyboard keyboard = makeKeyboard(buttons);
			
			Map<String, Object> trackingMap = new HashMap<String, Object>();
			trackingMap.put("user-id", trackingData.get("user-id").toString());
			trackingMap.put("reservation-id", trackingData.get("reservation-id").toString());
			trackingMap.put("user-name", trackingData.get("user-name").toString());
			trackingMap.put("step", "canceled");
			
			TrackingData newTrackingData = new TrackingData(trackingMap);
						
			return new TextMessage("Are you sure you want to cancel this reservation?", keyboard, newTrackingData, null);
		}
		
		return showReservation(trackingData, trackingData.get("reservation-id").toString());
	}

	public TextMessage cancellation(TrackingData trackingData, String sentMessage) throws Exception {
		
		if(sentMessage.equalsIgnoreCase("cancel reservation")){
			List<Map<String, Object>> buttons = new ArrayList<Map<String, Object>>();
			
			Map<String, Object> btn1 = makeButtonWhiteText(6, 1, "#675ca8", "reply", "Home", "Home");
			buttons.add(btn1);
			
			reservationService.delete(Long.parseLong(trackingData.get("reservation-id").toString()));
			
			MessageKeyboard keyboard = makeKeyboard(buttons);
			Map<String, Object> trackingMap = new HashMap<String, Object>();
			trackingMap.put("user-id", trackingData.get("user-id").toString());
			trackingMap.put("user-name", trackingData.get("user-name").toString());
			trackingMap.put("step", "init");

			TrackingData newTrackingData = new TrackingData(trackingMap);
			
			return new TextMessage("Reservation canceled.", keyboard, newTrackingData, null);
		}
			
		return confirmCancellation(trackingData, "cancel reservation");
	}
	
	
	/** 
	 * Helper methods
	**/
	
	private MessageKeyboard makeKeyboard(List<Map<String, Object>> buttons) {
		Map<String, Object> kbdMap = new HashMap<String, Object>();
		
		kbdMap.put("Type", "keyboard");
		kbdMap.put("Buttons", buttons);
		
		return new MessageKeyboard(kbdMap);
	}
		
	private Map<String, Object> makeButton(Integer columns, 
										   Integer rows, 
										   String bgColor, 
										   String actionType, 
										   String actionBody,
										   String text)
	{
		Map<String, Object> button = new HashMap<String, Object>();
		button.put("Columns", columns.toString());
		button.put("Rows", rows.toString());
		button.put("BgColor", bgColor);
		button.put("ActionType", actionType);
		button.put("ActionBody", actionBody);
		button.put("Text", "<font color='#494E67'>" + text + "</font>");
		button.put("TextVAlign", "middle");
		button.put("TextHAlign", "center");
		button.put("TextSize", "medium");
		
		return button;
	}
	
	private Map<String, Object> makeButtonWhiteText(Integer columns, 
			   		Integer rows, 
				   String bgColor, 
				   String actionType, 
				   String actionBody,
				   String text)
	{
	Map<String, Object> button = new HashMap<String, Object>();
	button.put("Columns", columns.toString());
	button.put("Rows", rows.toString());
	button.put("BgColor", bgColor);
	button.put("ActionType", actionType);
	button.put("ActionBody", actionBody);
	button.put("Text", "<font color='#FFFFFF'>" + text + "</font>");
	button.put("TextVAlign", "middle");
	button.put("TextHAlign", "center");
	button.put("TextSize", "medium");
	
	return button;
	}
	
	private boolean validateReservationDate(LocalDate date){
		if(date.isBefore(LocalDate.now()))
			return false;
		
		return true;
	}
	
}
