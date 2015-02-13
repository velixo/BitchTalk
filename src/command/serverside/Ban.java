package command.serverside;

import serverside.User;
import shared.command.Command;
import clientSide.Client;

public class Ban implements Command {
	private static final long serialVersionUID = 4591173403796749271L;
	private String username;
	
	public Ban(String username) {
		this.username = username;
	}

	@Override
	public void serverRun(User u) {
		if (u.isAdmin()) {
			u.getServer().ban(u, username);
		}
	}

	@Override
	public void clientRun(Client c) {
		
	}

	@Override
	public void clientRunRecieved(Client c) {
		
	}
}
