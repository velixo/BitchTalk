package command.clientside;

import statics.StaticVariables;
import clientSide.ClientGui;
import command.Command;

public class ClientMoveBitch implements Command {
	private ClientGui gui;
	
	public ClientMoveBitch(ClientGui gui) {
		this.gui = gui;
	}

	@Override
	public void run() {
		gui.playSound(StaticVariables.CLIENTBOSSASSBITCH);
	}

}
