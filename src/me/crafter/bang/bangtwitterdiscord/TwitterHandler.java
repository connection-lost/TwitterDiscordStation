package me.crafter.bang.bangtwitterdiscord;

import twitter4j.*;

public class TwitterHandler {
	
	public static void run(){
		TwitterStream steam = new TwitterStreamFactory().getInstance();
		steam.addListener(new TwitterStatusListener());
		steam.filter(new FilterQuery(DiscordWorker.ATTENTION));
	}
	
}
