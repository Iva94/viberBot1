package com.one.viberbot.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableAutoConfiguration 
public class MvcConfig extends WebMvcConfigurerAdapter {
	
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        
		registry.addViewController("/").setViewName("login");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/rooms/all").setViewName("room/rooms");
        registry.addViewController("/rooms/add").setViewName("room/add");
        registry.addViewController("/rooms/update").setViewName("room/update");
        
        registry.addViewController("/users").setViewName("users/users");
        registry.addViewController("/dashboard").setViewName("administration/dashboard");
        
    }
	
}
