package com.one.viberbot.service;

import com.viber.bot.Response;
import com.viber.bot.event.incoming.IncomingConversationStartedEvent;
import com.viber.bot.event.incoming.IncomingMessageEvent;
import com.viber.bot.event.incoming.IncomingSubscribedEvent;
import com.viber.bot.event.incoming.IncomingUnsubscribeEvent;
import com.viber.bot.message.Message;
import java.util.Optional;
import java.util.concurrent.Future;

public interface ViberBotService {

	public Future<Optional<Message>> onConversationStarted(IncomingConversationStartedEvent event);
	public void	onSubscribe(IncomingSubscribedEvent event, Response response);
	public void	onUnsubscribe(IncomingUnsubscribeEvent event);
	public void onMessageReceived(IncomingMessageEvent event, Message message, Response response);
	
}
