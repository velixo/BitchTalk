package command.serverside;

import serverside.Server;
import serverside.User;

import command.Command;

public class Unban implements Command {
	private Server s;
	private User unbanner;
	private String ip;
	
	public Unban(Server s, User unbanner, String ip) {
		this.s = s;
		this.unbanner = unbanner;
		this.ip = ip;
	}

	@Override
	public void run() {
		if (unbanner.isAdmin()) {
			s.unban(unbanner, ip);
		}
	}
}
