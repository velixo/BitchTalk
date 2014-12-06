package command.serverside;

import serverside.Server;

import command.Command;

public class Woolooloo implements Command {
	private Server server;
	
	public Woolooloo(Server s) {
		server = s;
	}

	@Override
	public void run() {
		// TODO implement woolooloo
	}
}
