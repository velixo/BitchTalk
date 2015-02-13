package command.serverside;

import serverside.User;
import shared.command.Command;
import shared.command.Message;
import clientSide.Client;

public class GetIp implements Command {
	private static final long serialVersionUID = 4696170861343360926L;
	private String username;
	
	public GetIp(String username) {
		this.username = username;
	}

	@Override
	public void serverRun(User u) {
		if (u.isAdmin()) {
			String ip = u.getServer().getIp(username);
			u.send(new Message("Bitch " + username + "'s IP is " + ip));
		}
	}

	@Override
	public void clientRun(Client c) {
		
	}

	@Override
	public void clientRunRecieved(Client c) {
		
	}
}
