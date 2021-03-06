package command.clientside;

import serverside.User;
import shared.command.Command;
import clientSide.Client;
import clientSide.ClientGui;

public class Unmute implements Command {
	private static final long serialVersionUID = 480681111321782893L;

	@Override
	public void clientRun(Client c) {
		ClientGui gui = c.getGui();
		gui.setMuteNotificationSound(false);
		gui.showSilentMessage("Notification sound unmuted, bitch.");
	}

	@Override
	public void serverRun(User u) {
		
	}

	@Override
	public void clientRunRecieved(Client c) {
		
	}

}
