package command.clientside;

import clientSide.ClientGui;
import command.Command;

public class ClientOpen implements Command{
	private ClientGui gui;
	
	public ClientOpen(ClientGui gui) {
		this.gui = gui;
	}

	@Override
	public void run() {
		gui.playSound("open");
	}

}
