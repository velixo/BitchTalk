package command.serverside;

import serverside.Server;

import command.Command;

public class ServerBossAssBitch implements Command {
	private Server s;
	
	public ServerBossAssBitch(Server s) {
		this.s = s;
	}

	@Override
	public void run() {
		s.broadcast("/:bossassbitch");
	}

}
