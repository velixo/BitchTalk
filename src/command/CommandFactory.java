package command;

import serverside.Server;
import serverside.User;

public class CommandFactory {
	public final static String SETNAME= "setname";
	
	public final static String WOOLOOLOO= "woolooloo";
	public final static String GAFFELTRUCK = "gaffeltruck";
	
	private Server server;

	public CommandFactory(Server s) {
		server = s;
	}
	
	public Command build(String input) {
		switch (input) {

		case SETNAME:
			return new SetName();
		
		case GAFFELTRUCK:
			return new Gaffeltruck(server);
			
		case WOOLOOLOO:
			break;
			
		default:
			break;
		}
		return null;
	}
}
