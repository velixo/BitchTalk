package command.clientside;

import clientSide.ClientGui;

import command.Command;

public class ClientBossAssBitch implements Command {
	private ClientGui g;
	
	public ClientBossAssBitch(ClientGui g) {
		this.g = g;
	}

	@Override
	public void run() {
		g.playSound("bossassbitch");
	}

}