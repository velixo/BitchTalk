package command.serverside;

import serverside.Server;

import command.Command;

public class ServerOpen implements Command {
	private Server s;
	
	public ServerOpen(Server s) {
		this.s = s;
	}

	@Override
	public void run() {
		s.broadcast("/:open");
	}

}
