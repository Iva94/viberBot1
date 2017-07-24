package com.one.viberbot.config;

import javax.annotation.Nullable;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.one.viberbot.service.ViberBotServiceImpl;
import com.viber.bot.ViberSignatureValidator;
import com.viber.bot.api.ViberBot;
import com.viber.bot.profile.BotProfile;

@Configuration
public class BotConfig implements ApplicationListener<ApplicationReadyEvent>{

	@Value("${application.viber-bot.auth-token}")
    private String authToken;

    @Value("${application.viber-bot.name}")
    private String name;

    @Nullable
    @Value("${application.viber-bot.avatar:@null}")
    private String avatar;

    @Bean
    ViberBot viberBot() {
        return new ViberBot(new BotProfile(name, avatar), authToken);
    }

    @Bean
    ViberSignatureValidator signatureValidator() {
        return new ViberSignatureValidator(authToken);
    }
    
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
}}
