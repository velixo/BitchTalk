package command.clientside;

import statics.StaticVariables;
import clientSide.ClientGui;
import command.Command;

public class ClientWhatsGoingOn implements Command {
	private ClientGui g;
	
	public ClientWhatsGoingOn(ClientGui g) {
		this.g = g;
	}

	@Override
	public void run() {
		g.playSound(StaticVariables.WHATSGOINGON);
	}

	
}
