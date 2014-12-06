package command;

import serverside.Server;

public class Gaffeltruck implements Command {
	private Server server;
	private final static String message = "hejsan här kommer en gaffeltruck\n";
	
	public Gaffeltruck(Server s) {
		server = s;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		server.broadcast(message);
	}

}
