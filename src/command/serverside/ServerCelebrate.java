package command.serverside;

import serverside.Server;

import command.Command;

public class ServerCelebrate implements Command {
	private Server s;
	
	public ServerCelebrate(Server s) {
		this.s = s;
	}

	@Override
	public void run() {
		s.broadcast("/:celebrate");
	}

}
