package command.clientside;

import clientSide.ClientGui;

import command.Command;

public class ClientWoolooloo implements Command {
	private ClientGui gui;

	public ClientWoolooloo(ClientGui g) {
		gui = g;
	}

	@Override
	public void run() {
		gui.playSound("woolooloo");
	}
}
