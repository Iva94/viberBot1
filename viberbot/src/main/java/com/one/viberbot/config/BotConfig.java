package com.one.viberbot.config;

import javax.annotation.Nullable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.viber.bot.ViberSignatureValidator;

@Configuration
public class BotConfig {

	@Value("${application.viber-bot.auth-token}")
	private String authToken;
	
	@Value("${application.viber-bot.name}")
	private String name;
	
	@Nullable
	@Value("${application.viber-bot.avatar:@null}")
	    private String avatar;
	
	//@Bean
	//ViberBot viberBot() {
	//    return new ViberBot(new BotProfile(name, avatar), authToken);
	// }
	
	@Bean
	ViberSignatureValidator signatureValidator() {
	    return new ViberSignatureValidator(authToken);
	}
}
