package command.clientside;

import clientSide.ClientGui;
import command.Command;

public class Mute implements Command {
	private ClientGui c;
	
	public Mute(ClientGui c) {
		this.c = c;
	}

	@Override
	public void run() {
		c.setMuteNotificationSound(true);
		c.showMessage("Notification sound muted, bitch.");
	}

}
