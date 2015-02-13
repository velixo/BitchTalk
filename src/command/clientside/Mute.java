package command.clientside;

import serverside.User;
import clientSide.Client;
import clientSide.ClientGui;
import command.Command;

public class Mute implements Command {
	private static final long serialVersionUID = 213503265396113746L;

	@Override
	public void clientRun(Client c) {
		ClientGui gui = c.getGui();
		gui.setMuteNotificationSound(true);
		gui.showSilentMessage("Notification sound muted, bitch.");
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
