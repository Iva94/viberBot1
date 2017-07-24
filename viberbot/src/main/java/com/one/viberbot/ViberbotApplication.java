package com.one.viberbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

import com.one.viberbot.service.ViberBotServiceImpl;
import com.viber.bot.api.ViberBot;
import javax.inject.Inject;


@SpringBootApplication
public class ViberbotApplication implements ApplicationListener<ApplicationReadyEvent>{

	@Inject
	private ViberBot bot;
	
	@Value("${application.viber-bot.webhook-url}")
	private String webhookUrl;
	
	@Autowired 
	private ViberBotServiceImpl viberService;
	
	public static void main(String[] args) {
		SpringApplication.run(ViberbotApplication.class, args);
	}
	
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
