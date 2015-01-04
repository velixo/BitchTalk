package command.clientside;

import clientSide.ClientGui;

import command.Command;

public class AlreadyConnected implements Command {
	private ClientGui gui;
	
	public AlreadyConnected(ClientGui g) {
		gui = g;
	}

	@Override
	public void run() {
		gui.showSilentMessage("Bitch, you're already fucking connected. Get a fucking grip.");
	}

}
