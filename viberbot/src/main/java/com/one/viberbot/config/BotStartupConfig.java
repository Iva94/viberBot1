package com.one.viberbot.config;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

import com.one.viberbot.service.ViberBotServiceImpl;
import com.viber.bot.api.ViberBot;


public class BotStartupConfig implements ApplicationListener<ApplicationReadyEvent> {
	
	@Inject
    private ViberBot bot;

    @Value("${application.viber-bot.webhook-url}")
    private String webhookUrl;
	
    @Autowired
    private ViberBotServiceImpl viberService;
    
	@Override
    public void onApplicationEvent(ApplicationReadyEvent appReadyEvent) {
        try {
            bot.setWebhook(webhookUrl).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        bot.onMessageReceived((event, message, response) -> viberService.onMessageReceived(event, message, response));
        bot.onConversationStarted(event -> viberService.onConversationStarted(event));
    }

}