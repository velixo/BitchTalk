package command;

import command.serverside.ServerCommandFactory;

import serverside.User;
import clientSide.Client;

public class UnrecognizedCommand implements Command {
	private static final long serialVersionUID = -8445019066399527186L;
	private String input;
	
	public UnrecognizedCommand(String input) {
		this.input = input;
	}

	@Override
	public void serverRun(User u) {
		Command c = ServerCommandFactory.build(input, u);
		c.serverRun(u);
	}

	@Override
	public void clientRun(Client c) {
		c.send(this);
	}

	@Override
	public void clientRunRecieved(Client c) {
		// TODO Auto-generated method stub
		
	}

}
