package command.serverside;

import serverside.User;
import clientSide.Client;

import command.Command;
import command.Message;

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
			//TODO send is dangerous - fixed, i think
			u.send(new Message("Bitch " + username + "'s IP is " + ip));
		}
	}

	@Override
	public void clientRun(Client c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clientRunRecieved(Client c) {
		// TODO Auto-generated method stub
		
	}
}
