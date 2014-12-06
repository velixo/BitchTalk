package command;

import serverside.Server;
import serverside.User;

import command.clientside.SetName;

public class UserCommandFactory {
	public final static String SETNAME= "setname";
	private Server server;
	private User u;

	public UserCommandFactory(User u) {
		this.u = u;
	}
	
	public Command build(String input) {
		switch (input) {

		case SETNAME:
			return new SetName(u, input);
		
		default:
			break;
		}
		return null;
	}
}
