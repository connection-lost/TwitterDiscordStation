package me.crafter.bang.bangtwitterdiscord;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.util.MessageBuilder;

public class DiscordWorker {
	
	public static IDiscordClient client;
	public static long GUILD_BANG = 299652406109536258L;
	
	public static long CHANNEL_CHAT = 299652406109536258L;
	public static long CHANNEL_CAR = 299652640567197696L;
	public static long CHANNEL_GJ = 300024407814963210L;
	public static long CHANNEL_CJ = 356551718072352771L;
	public static long CHANNEL_CGSS = 324695241489645570L;
	public static long CHANNEL_SIF = 389471375041101829L; // TODO new channel
	
	public static String[] ATTENTION = {"バンドリ協力", "ガルパ協力", "デレステ協力", "なかよしマッチ", "スクフェス協力"};

	public static String GJ = "ベテラン";
	public static String CJ = "マスター";
	public static String CGSS = "デレステ協力";
	public static String SIF1 = "なかよしマッチ";
	public static String SIF2 = "スクフェス協力";
	
    public static IDiscordClient getClient(String token) throws Exception {
        ClientBuilder clientBuilder = new ClientBuilder();
        clientBuilder.withToken(token);
        return clientBuilder.login();
	}

    public static void sendMessage(final long channel, final String message){
    	if (message == null || message.equals("")) return;
    	if (client == null) return;
    	try {
			new MessageBuilder(client).
			withChannel(channel).
			withCode("Markdown", message).
			build();
			System.out.println("[Discord] Sent: " + 
					(message.contains("\n") ? message.split("\n")[0] : message));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public static void connect(){
    	disconnect();
		try {
			InputStream input = new FileInputStream("discord.properties");
			Properties prop = new Properties();
			prop.load(input);
			DiscordWorker.client = DiscordWorker.getClient(prop.getProperty("oauth.token"));
		} catch (Exception e) {
			DiscordWorker.client = null;
			e.printStackTrace();
		}
    }
    
    public static void disconnect(){
    	try {
			DiscordWorker.client.logout();
		} catch (Exception e) {
			System.out.println("Client is not running, skip disconnect");
			DiscordWorker.client = null;
		}
    	DiscordWorker.client = null;
    }
    
    public static void reconnect(){
    	disconnect();
    	try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {}
    	connect();
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
