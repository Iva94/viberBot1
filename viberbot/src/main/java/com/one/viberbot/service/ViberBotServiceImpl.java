package com.one.viberbot.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.common.util.concurrent.Futures;
import com.one.viberbot.database.entity.AppUser;
import com.viber.bot.Response;
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
	private UserServiceImpl userService;
	
	@Autowired
	private ConversationService conversationService;
	
	@Override
	public Future<Optional<Message>> onConversationStarted(IncomingConversationStartedEvent event) {
		TextMessage message;
		
		message = conversationService.conversationStartedMessage(event);
		
		UserProfile usr = event.getUser();
		
		userService.add(new AppUser(usr.getId(), usr.getName(), false));
		userService.subscribe(usr.getId());
		
		return Futures.immediateFuture(Optional.of(message));
	}
	
	@Override
	public void onSubscribe(IncomingSubscribedEvent event, Response response) {
				
		UserProfile usr = event.getUser();
		
		userService.add(new AppUser(usr.getId(), usr.getName(), false));
		
		userService.subscribe(usr.getId());
		response.send(new TextMessage("You are now subscribed."));
	}

	@Override
	public void onUnsubscribe(IncomingUnsubscribeEvent event) {
		userService.unsubscribe(event.getUserId());
	}

	@Override
	public void onMessageReceived(IncomingMessageEvent event, Message message, Response response) {
		TextMessage responseMessage = conversationService.newMessage(message.getTrackingData(), message);
		
		response.send(responseMessage);
	}
	
}
