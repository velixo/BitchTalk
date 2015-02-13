package command.serverside;

import serverside.User;
import clientSide.Client;

import command.Command;
import command.Message;

public class Gaffeltruck implements Command {
	private static final long serialVersionUID = 4620349428799104021L;
	private final static String message = "hejsan här kommer en gaffeltruck\n";

	@Override
	public void serverRun(User u) {
		u.getServer().broadcast(new Message(message));
	}

	@Override
	public void clientRun(Client c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clientRunRecieved(Client c) {
		// TODO Auto-generated method stub
		
	}}
