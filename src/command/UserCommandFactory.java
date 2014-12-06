package command;

import serverside.Server;

import command.clientside.SetName;
import command.serverside.Gaffeltruck;

public class UserCommandFactory {
	public final static String SETNAME= "setname";
	private Server server;

	public UserCommandFactory(Server s) {
		server = s;
	}
	
	public Command build(String input) {
		switch (input) {

		case SETNAME:
			return new SetName();
		
		default:
			break;
		}
		return null;
	}
}
