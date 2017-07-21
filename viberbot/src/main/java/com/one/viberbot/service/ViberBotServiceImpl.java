package com.one.viberbot.service;

import java.util.Optional;
import java.util.concurrent.Future;

import javax.annotation.Nonnull;

import org.springframework.beans.factory.annotation.Autowired;

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
import com.viber.bot.message.TextMessage;

public class ViberBotServiceImpl implements ViberBotService {
	
	@Autowired 
	private RoomServiceImpl roomService;
	
	@Autowired
	private UserServiceImpl userService;
	
	
	
	@Override
	public Future<Optional<Message>> onConversationStarted(IncomingConversationStartedEvent event) {
		//return new TextMessage("Hi");
	}

	@Override
	public void onSubscribe(IncomingSubscribedEvent event, Response response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUnsubscribe(IncomingUnsubscribeEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMessageReceived(IncomingMessageEvent event, Message message, Response response) {
		// TODO Auto-generated method stub
		
	}
	
}
