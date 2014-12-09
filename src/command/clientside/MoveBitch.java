package command.clientside;

import clientSide.ClientGui;

import command.Command;

public class MoveBitch implements Command {
	private ClientGui gui;
	
	public MoveBitch(ClientGui gui) {
		this.gui = gui;
	}

	@Override
	public void run() {
		gui.playSound("movebitchgetoutdaway");
	}

}
