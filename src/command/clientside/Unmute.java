package command.clientside;

import serverside.User;
import clientSide.Client;
import clientSide.ClientGui;
import command.Command;

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clientRunRecieved(Client c) {
		// TODO Auto-generated method stub
		
	}

}
