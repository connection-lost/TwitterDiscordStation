package me.crafter.bang.bangtwitterdiscord;

import java.util.TimerTask;

public class RestartTask extends TimerTask {

	@Override
	public void run() {
		System.out.println("Restarting...");
		System.exit(0);
	}

}
