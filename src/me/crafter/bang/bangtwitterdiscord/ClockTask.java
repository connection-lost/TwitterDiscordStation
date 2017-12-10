package me.crafter.bang.bangtwitterdiscord;

import java.util.TimerTask;

public class ClockTask extends TimerTask {

	@Override
	public void run() {
		if (DiscordWorker.client != null && DiscordWorker.client.isReady()){
			DiscordWorker.client.changePlayingText("¥¬¥ë¥Ñ£¡");
		}
	}

}
