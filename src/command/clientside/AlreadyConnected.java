package command.clientside;

import serverside.User;
import clientSide.Client;

import command.Command;

public class AlreadyConnected implements Command {
	private static final long serialVersionUID = -2547759676000232801L;

	@Override
	public void clientRun(Client c) {
		c.getGui().showSilentMessage("Bitch, you're already connected. Get a fucking grip.");
	}

	@Override
	public void serverRun(User u) {
		
	}

	@Override
	public void clientRunRecieved(Client c) {
		
	}
}
