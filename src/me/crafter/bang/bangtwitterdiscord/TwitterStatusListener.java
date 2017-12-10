package me.crafter.bang.bangtwitterdiscord;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

public class TwitterStatusListener implements StatusListener {
	
	public void onException(Exception arg0) {}
	public void onDeletionNotice(StatusDeletionNotice arg0) {}
	public void onScrubGeo(long arg0, long arg1) {}
	public void onStallWarning(StallWarning arg0) {}
	public void onTrackLimitationNotice(int arg0){}
	public void onStatus(Status status) {
		String fulltext = status.getText();
		Long channel = getTargetChannel(fulltext);
		if (channel == 0L) return; // Unmatched tweet
		DiscordWorker.sendMessage(channel, formatMessage(status));
	}
	
	public static String formatMessage(Status status){
		StringBuilder builder = new StringBuilder();
		
		// User name & time line
		builder.append("# ");
		builder.append(getTime());
		builder.append(" ");
		builder.append("[" + status.getUser().getScreenName() + "]");
		builder.append(" ");
		builder.append("@" + status.getUser().getName());
		builder.append("\n");
		
		// Message info
		String trimmed = status.getText().split("#")[0].trim();
		builder.append(trimmed);
		
		return builder.toString();
	}
	
	public static String getTime(){
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public long getTargetChannel(String message){
		if (!message.contains("#")) return 0L;
		if (!formatCheck(message)) return 0L;
		if (message.contains(DiscordWorker.SIF1) || message.contains(DiscordWorker.SIF2)){ // LoveLive
			return DiscordWorker.CHANNEL_SIF;
		} else if (message.contains(DiscordWorker.CGSS)){ // CGSS
			return DiscordWorker.CHANNEL_CGSS;
		} else { // Bang
			if (message.contains(DiscordWorker.CJ)){ // Super
				return DiscordWorker.CHANNEL_CJ;
			} else if (message.contains(DiscordWorker.GJ)){ // Veteran
				return DiscordWorker.CHANNEL_GJ;
			} else { // Everyone
				return DiscordWorker.CHANNEL_CAR;
			}
		}
	}
	
	public boolean formatCheck(String message){
		Pattern p = Pattern.compile("(?<![0-9,£°,£±,£²,£³,£´,£µ,£¶,£·,£¸,£¹])[0-9,£°,£±,£²,£³,£´,£µ,£¶,£·,£¸,£¹]{5,7}(?![0-9,£°,£±,£²,£³,£´,£µ,£¶,£·,£¸,£¹])");
		Matcher m = p.matcher(message);
		if (m.find()){
			return true;
		} else {
			return false;
		}
	}
	
}
