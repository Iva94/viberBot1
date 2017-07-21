package com.one.viberbot.service;

import javax.annotation.Nonnull;

import org.springframework.beans.factory.annotation.Autowired;

import com.one.viberbot.database.repository.ReservationRepository;
import com.one.viberbot.database.repository.ViberBotRepository;
import com.viber.bot.event.Event;
import com.viber.bot.event.callback.OnConversationStarted;
import com.viber.bot.event.callback.OnSubscribe;

public class ViberBotServiceImpl implements ViberBotService {

	@Autowired
	private ViberBotRepository viberBotRepository;
	
	public void onConversationStarted(final @Nonnull OnConversationStarted listener) {
	      //  on(Event.CONVERSATION_STARTED, listener);

	}


	public void onSubscribe(final @Nonnull OnSubscribe listener) {
        //on(Event.SUBSCRIBED, listener);

	}

	@Override
	public void onUnsubscribe() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMessageReceived() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onConversationStarted() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onSubscribe() {
		// TODO Auto-generated method stub
		
	}

}
