package command.serverside;

import serverside.Server;
import serverside.User;

import command.Command;

public class Ban implements Command {
	private Server s;
	private User u;
	private String username;
	
	public Ban(Server s, User u, String username) {
		this.s = s;
		this.u = u;
		this.username = username;
	}

	@Override
	public void run() {
		if (u.isAdmin()) {
			s.ban(username);
		}
	}

}
