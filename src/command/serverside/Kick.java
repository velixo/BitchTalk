package command.serverside;

import serverside.User;
import shared.command.Command;
import clientSide.Client;

public class Kick implements Command {
	private static final long serialVersionUID = -4476051316009716491L;
	private String username;
	
	public Kick(String username) {
		this.username = username;
	}

	@Override
	public void serverRun(User u) {
		if (u.isAdmin()) {
			u.getServer().kick(u, username);
		}
	}

	@Override
	public void clientRun(Client c) {
		
	}

	@Override
	public void clientRunRecieved(Client c) {
		
	}
}
