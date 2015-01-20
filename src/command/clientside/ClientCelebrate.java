package command.clientside;

import statics.StaticVariables;
import clientSide.ClientGui;
import command.Command;



public class ClientCelebrate implements Command {
	private ClientGui gui;
	
	public ClientCelebrate(ClientGui gui) {
		this.gui = gui;
	}

	@Override
	public void run() {
		gui.playSound(StaticVariables.CELEBRATE);
	}

}
