package me.crafter.bang.bangtwitterdiscord;

import java.util.Timer;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;

public class Main {

	static Timer timer = new Timer();
	
    public static void main(String args[]){
    	// Setup Discord
    	// Connect
    	DiscordWorker.connect();
    	wait(4000);
    	System.out.println("Fetched guilds:");
    	for (IGuild guild : DiscordWorker.client.getGuilds()){
    		System.out.println(">" + guild.getName() + ": " + guild.getLongID());
    		for (IChannel channel : guild.getChannels()){
        		System.out.println(">>" + channel.getName() + ": " + channel.getLongID());
        	}
    	}
    	// Status clock task
    	timer.schedule(new ClockTask(), 1000, 1000*60);
    	
    	// Setup Twitter
    	TwitterHandler.run();
    	
    	// Setup restart task
		timer.schedule(new RestartTask(), 1000*3600);
    }
    
    public static void wait(int time){
    	try {
        	System.out.println("Waiting... (" + time + ")");
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }

}
