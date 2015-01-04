package command.clientside;

import clientSide.ClientGui;
import command.Command;

public class ClientWhatsGoingOn implements Command {
	private ClientGui gui;
	
	public ClientWhatsGoingOn(ClientGui gui) {
		this.gui = gui;
	}

	@Override
	public void run() {
		gui.playSound("whatsgoingon");
	}

	
}
