package command.serverside;

import serverside.Server;

import command.Command;

public class ServerWoolooloo implements Command {
	private Server server;
	
	public ServerWoolooloo(Server s) {
		server = s;
	}

	@Override
	public void run() {
		server.broadcast("/:woolooloo");
	}
}
