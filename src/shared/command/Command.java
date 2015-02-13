package shared.command;

import java.io.Serializable;

import serverside.User;
import clientSide.Client;

public interface Command extends Serializable {
	public void serverRun(User u);
	public void clientRun(Client c);
	public void clientRunRecieved(Client c);
}
