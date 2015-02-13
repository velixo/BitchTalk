package command.serverside;

import serverside.User;
import shared.command.Command;
import clientSide.Client;

public class Unban implements Command {
	private static final long serialVersionUID = 1738028001989064575L;
	private String ip;
	
	public Unban(String ip) {
		this.ip = ip;
	}

	@Override
	public void serverRun(User u) {
		if (u.isAdmin()) {
			u.getServer().unban(u, ip);
		}
	}

	@Override
	public void clientRun(Client c) {
		
	}

	@Override
	public void clientRunRecieved(Client c) {
		
	}
}
