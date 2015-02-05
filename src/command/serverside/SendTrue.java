package command.serverside;

import serverside.Server;
import serverside.User;
import statics.StaticVariables;
import command.Command;

public class SendTrue implements Command {
	private Server server;
	private User u;
	private String input;
	
	public SendTrue(Server s, User u, String input) {
		server = s;
		this.u = u;
		this.input = input;
	}

	@Override
	public void run() {
		if (u.isAdmin()) {
			String unaliasedMessage = input.replace(StaticVariables.SENDTRUE + " ", "");
			System.out.println("input = " + input);
			System.out.println("unaliasedMessage = " + unaliasedMessage);
			server.broadcast(u.getName() + ": " + unaliasedMessage);
		}
	}

}
