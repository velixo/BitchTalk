package command.serverside;

import serverside.User;
import shared.command.Command;
import shared.command.Message;
import clientSide.Client;

public class Gaffeltruck implements Command {
	private static final long serialVersionUID = 4620349428799104021L;
	private final static String message = "hejsan h�r kommer en gaffeltruck\n";

	@Override
	public void serverRun(User u) {
		u.getServer().broadcast(new Message(message));
	}

	@Override
	public void clientRun(Client c) {
		//TODO implement
	}

	@Override
	public void clientRunRecieved(Client c) {
		//TODO implement
	}
}
