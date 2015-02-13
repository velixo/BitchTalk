package command.serverside;

import java.util.HashMap;
import java.util.Set;

import serverside.User;
import clientSide.Client;

import command.Command;
import command.Message;

public class GetAliases implements Command {
	private static final long serialVersionUID = -1135758666242445802L;

	@Override
	public void serverRun(User user) {
		if (user.isAdmin()) {
			HashMap<String, String> aliases = user.getServer().getAliasizer().getAliases();
			Set<String> words = aliases.keySet();
			String message = "";
			for (String word : words) {
				message += word + " => " + aliases.get(word) + "\n";
			}
			//TODO send is dangerous - fixed, i think
			user.send(new Message(message));
		}
	}

	@Override
	public void clientRun(Client c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clientRunRecieved(Client c) {
		// TODO Auto-generated method stub
		
	}
}
