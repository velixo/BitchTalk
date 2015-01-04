package command.serverside;

import serverside.Server;
import serverside.User;

import command.Command;

public class ServerWhatsGoingOn implements Command {
	private Server s;
	private User u;
	
	public ServerWhatsGoingOn(Server s, User u) {
		this.s = s;
		this.u = u;
	}

	@Override
	public void run() {
		if (u.isAdmin()) {
			s.broadcast("/:whatsgoingon");
		}
	}

}
