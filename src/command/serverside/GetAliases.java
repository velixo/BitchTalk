package command.serverside;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import serverside.Server;
import serverside.User;

import command.Command;

public class GetAliases implements Command {
	private Server server;
	private User user;
	
	
	public GetAliases(Server s, User u) {
		server = s;
		user = u;
	}

	@Override
	public void run() {
		if (user.isAdmin()) {
			HashMap<String, String> aliases = server.getAliasizer().getAliases();
			Set<String> words = aliases.keySet();
			String message = "";
			for (String word : words) {
				message += word + " => " + aliases.get(word) + "\n";
			}
			user.send(message);
		}
	}

}
