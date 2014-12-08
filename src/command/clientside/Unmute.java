package command.clientside;

import clientSide.ClientGui;

import command.Command;

public class Unmute implements Command {
private ClientGui c;
	
	public Unmute(ClientGui c) {
		this.c = c;
	}

	@Override
	public void run() {
		c.setMuteNotificationSound(false);
		c.showMessage("Notification sound unmuted, bitch.");
	}

}
