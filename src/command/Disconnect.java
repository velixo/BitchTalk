package command;

import serverside.User;
import clientSide.Client;

public class Disconnect implements Command {

	@Override
	public void serverRun(User u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clientRun(Client c) {
		c.closeCrap();
	}

	@Override
	public void clientRunRecieved(Client c) {
		// TODO Auto-generated method stub
		
	}

}
