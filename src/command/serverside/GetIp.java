package command.serverside;

import serverside.Server;
import serverside.User;

import command.Command;

public class GetIp implements Command {
	private Server s;
	private User u;
	private String username;
	
	public GetIp(Server s, User u, String username) {
		this.s = s;
		this.u = u;
		this.username = username;
	}

	@Override
	public void run() {
		if (u.isAdmin()) {
			String ip = s.getIp(username);
			//TODO send is dangerous - fixed, i think
			u.send("Bitch " + username + "'s IP is " + ip);
		}
	}

}
