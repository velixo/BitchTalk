package command.serverside;

import serverside.Server;
import serverside.User;

import command.Command;

public class Ban implements Command {
	private Server s;
	private User unbanner;
	private String username;
	
	public Ban(Server s, User unbanner, String username) {
		this.s = s;
		this.unbanner = unbanner;
		this.username = username;
	}

	@Override
	public void run() {
		if (unbanner.isAdmin()) {
			s.ban(unbanner, username);
		}
	}

}
