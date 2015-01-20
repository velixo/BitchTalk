package command.clientside;

import statics.StaticVariables;
import clientSide.ClientGui;
import command.Command;

public class ClientOpen implements Command{
	private ClientGui g;
	
	public ClientOpen(ClientGui g) {
		this.g = g;
	}

	@Override
	public void run() {
		g.playSound(StaticVariables.OPEN);
	}

}
