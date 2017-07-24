package com.one.viberbot.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Future;

import javax.annotation.Nonnull;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Service;

import com.google.common.util.concurrent.Futures;
import com.one.viberbot.database.entity.AppUser;
import com.one.viberbot.database.repository.ReservationRepository;
import com.one.viberbot.database.repository.ViberBotRepository;
import com.viber.bot.Response;
import com.viber.bot.event.Event;
import com.viber.bot.event.callback.OnConversationStarted;
import com.viber.bot.event.callback.OnSubscribe;
import com.viber.bot.event.incoming.IncomingConversationStartedEvent;
import com.viber.bot.event.incoming.IncomingMessageEvent;
import com.viber.bot.event.incoming.IncomingSubscribedEvent;
import com.viber.bot.event.incoming.IncomingUnsubscribeEvent;
import com.viber.bot.message.Message;
import com.viber.bot.message.MessageKeyboard;
import com.viber.bot.message.TextMessage;
import com.viber.bot.message.TrackingData;
import com.viber.bot.profile.UserProfile;

@Service
public class ViberBotServiceImpl implements ViberBotService {
	
	@Autowired 
	private RoomServiceImpl roomService;
	
	@Autowired 
	private ReservationService reservationService;
	
	@Autowired
	private UserServiceImpl userService;
	
	private LocalDate date;
	
	
	@Override
	public Future<Optional<Message>> onConversationStarted(IncomingConversationStartedEvent event) {
		TextMessage message = new TextMessage("An error occurred, please try again later.");
		
		try{
			Map<String, Object> kbdMap = new HashMap<String, Object>();
			
			List<Map<String, Object>> buttons = new ArrayList<Map<String, Object>>();
			
			Map<String, Object> btn1 = new HashMap<String, Object>();
			btn1.put("Columns", "3");
			btn1.put("Rows", "2");
			btn1.put("BgColor", "#f6f7f9");
			btn1.put("ActionType", "reply");
			btn1.put("ActionBody", "Make a reservation");
			btn1.put("Text", "<font color=\\\"#494E67\\\">Make a reservation</font>");
			btn1.put("TextVAlign", "middle");
			btn1.put("TextHAlign", "center");
			btn1.put("TextSize", "medium");
			
			
			Map<String, Object> btn2 = new HashMap<String, Object>();
			btn2.put("Columns", "3");
			btn2.put("Rows", "2");
			btn2.put("BgColor", "#f6f7f9");
			btn2.put("ActionType", "reply");
			btn2.put("ActionBody", "View all reservations");
			btn2.put("Text", "<font color=\\\"#494E67\\\">View all reservations</font>");
			btn2.put("TextVAlign", "middle");
			btn2.put("TextHAlign", "center");
			btn2.put("TextSize", "medium");
			
			buttons.add(btn1);
			buttons.add(btn2);
			
			kbdMap.put("Type", "keyboard");
			kbdMap.put("Buttons", buttons);
			
			MessageKeyboard keyboard = new MessageKeyboard(kbdMap);
			
			Map<String, Object> trackingMap = new HashMap<String, Object>();
			trackingMap.put("step", "1");
			
			TrackingData trackingData = new TrackingData(trackingMap);
			
			message = new TextMessage("Hello " + event.getUser().getName() + "!", keyboard, trackingData, null);
		
		}catch(Exception e){
			message = new TextMessage("Error! Please try again.");
		}
		
		return Futures.immediateFuture(Optional.of(message));
	}

	
	@Override
	public void onSubscribe(IncomingSubscribedEvent event, Response response) {
		UserProfile usr = event.getUser();
		
		userService.add(new AppUser(usr.getId(), usr.getName(), false));
		userService.subscribe(usr.getId());
	}

	
	@Override
	public void onUnsubscribe(IncomingUnsubscribeEvent event) {
		userService.unsubscribe(event.getUserId());
	}

	
	@Override
	public void onMessageReceived(IncomingMessageEvent event, Message message, Response response) {
		String sentMessage = message.getMapRepresentation().get("text").toString();
		
		String tracking = message.getTrackingData().get("step").toString();
		
		if(tracking.equals("1")){
			if(sentMessage.equals("Make a reservation")){
				TextMessage responseMessage = new TextMessage("Please enter a date for a reservation (format: dd.mm.yyyy)");
				Map<String, Object> trackingMap = new HashMap<String, Object>();
				trackingMap.put("step", "Enter date");
				TrackingData trackingData = new TrackingData(trackingMap);
				response.send(responseMessage);
			}
			else if(sentMessage.equals("View all reservations")){
				TextMessage responseMessage = new TextMessage("Viewing all reservations...");
				
				response.send(responseMessage);
			}
			else{
				TextMessage responseMessage = new TextMessage("Show options again.");
				
				response.send(responseMessage);
			}
		}
		
		else if(tracking.equals("Enter date"))
		{
			try{
				date = LocalDate.parse(sentMessage, DateTimeFormatter.ISO_LOCAL_DATE);				
			}
			catch(Exception e)
			{	
				message = new TextMessage("Error! Please try again.");
			}
			TextMessage responseMessage = new TextMessage("Please enter room number for a reservation!");
			//responseMessage = new TextMessage(roomService.findAll().toString());
			
			
			
			Map<String, Object> trackingMap = new HashMap<String, Object>();
			trackingMap.put("step", "Select room");
			TrackingData trackingData = new TrackingData(trackingMap);
			response.send(responseMessage);
		}
		else if(tracking.equals("Select room"))
		{
			Long roomId = Long.valueOf(sentMessage);
			TextMessage responseMessage = new TextMessage(reservationService.getFreeRoomCapacitiesOnDate(roomId, date).toString());
			
			response.send(responseMessage);
		}

		
		
	}
	
}
