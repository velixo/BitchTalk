package command.serverside;

import java.util.List;

import serverside.Server;
import serverside.User;
import clientSide.Client;

import command.Command;
import command.Message;

public class GetBannedList implements Command {
	private static final long serialVersionUID = -3636490112618741394L;

	@Override
	public void serverRun(User u) {
		if (u.isAdmin()) {
			Server server = u.getServer();
			List<String> bannedList = server.getBannedList();
			
			StringBuilder message = new StringBuilder("The following IP's are banned: \n");
			for (String ip : bannedList) {
				message.append(ip + "\n");
			}
			u.send(new Message(message.toString()));
		}
	}

	@Override
	public void clientRun(Client c) {
		
	}

	@Override
	public void clientRunRecieved(Client c) {
		
	}
}
