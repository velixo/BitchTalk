package command;

import java.util.StringTokenizer;

import serverside.Server;
import serverside.User;

import command.clientside.SetName;

public class UserCommandFactory {
	public final static String SETNAME= "/setname";
	private Server server;
	private User u;

	public UserCommandFactory(User u) {
		this.u = u;
	}
	
	public Command build(String input) {
		StringTokenizer st = new StringTokenizer(input);
		switch (st.nextToken()) {
		case SETNAME:
			return new SetName(u, st.nextToken());
		
		default:
			return new NotACommand(u);
		}
	}
}
