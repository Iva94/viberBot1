package com.one.viberbot.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.one.viberbot.database.repository.ReservationRepository;
import com.one.viberbot.database.repository.ViberBotRepository;

public class ViberBotServiceImpl implements ViberBotService {

	@Autowired
	private ViberBotRepository viberBotRepository;
	
	@Override
	public void onConversationStarted() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSubscribe() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUnsubscribe() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMessageReceived() {
		// TODO Auto-generated method stub
		
	}

}
