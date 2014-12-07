package command.clientside;

import clientSide.ClientGui;

import command.Command;

public class ToggleMute implements Command {
	private ClientGui c;
	
	public ToggleMute(ClientGui c) {
		this.c = c;
	}

	@Override
	public void run() {
		c.toggleMuteNotificationSound();
		c.showMessage("Mute toggled, bitch.");
	}

}
