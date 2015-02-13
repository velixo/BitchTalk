package command;

import serverside.User;
import clientSide.Client;

public class Disconnect implements Command {
	private static final long serialVersionUID = -6073388303812926805L;

	@Override
	public void serverRun(User u) {
		
	}

	@Override
	public void clientRun(Client c) {
		c.closeCrap();
	}

	@Override
	public void clientRunRecieved(Client c) {
		
	}

}
