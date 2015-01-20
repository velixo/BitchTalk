package command.clientside;

import statics.StaticVariables;
import clientSide.ClientGui;
import command.Command;

public class ClientMoveBitch implements Command {
	private ClientGui g;
	
	public ClientMoveBitch(ClientGui g) {
		this.g = g;
	}

	@Override
	public void run() {
		g.playSound(StaticVariables.SERVERBOSSASSBITCH);
	}

}
