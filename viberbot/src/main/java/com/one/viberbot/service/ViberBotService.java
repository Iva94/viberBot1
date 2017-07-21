package com.one.viberbot.service;

public interface ViberBotService {

	public void onConversationStarted();
	public void	onSubscribe();
	public void	onUnsubscribe();
	public void onMessageReceived();
	
}
