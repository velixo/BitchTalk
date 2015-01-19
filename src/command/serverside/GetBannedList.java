package command.serverside;

import serverside.Server;
import serverside.User;

import command.Command;

public class GetBannedList implements Command {
	private Server s;
	private User u;
	
	public GetBannedList(Server s, User u) {
		this.s = s;
		this.u = u;
	}

	@Override
	public void run() {
		if (u.isAdmin()) {
			s.sendBannedList(u);
		}
	}
	
	
}
