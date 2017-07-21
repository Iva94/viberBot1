package com.one.viberbot.database.entity;

public class BotProfile {
	private String name;
    private String avatar;

    public BotProfile(String name, String avatar) {
        this.name = name;
        this.avatar = avatar;
    }

    public BotProfile(String name) {
        this(name, null);
    }

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }
}
