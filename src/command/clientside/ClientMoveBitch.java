package command.clientside;

import clientSide.ClientGui;

import command.Command;

public class ClientMoveBitch implements Command {
	private ClientGui gui;
	
	public ClientMoveBitch(ClientGui gui) {
		this.gui = gui;
	}

	@Override
	public void run() {
		gui.playSound("movebitchgetoutdaway");
	}

}
