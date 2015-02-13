package command.serverside;

import clientSide.Client;
import serverside.Server;
import serverside.User;
import command.Command;
import command.Message;

public class SetName implements Command {
	private static final long serialVersionUID = -6606767886670860536L;
	private String newName;
	
	public SetName(Server s, User u, String n){
		newName = n;
	}
	
	@Override
	public void serverRun(User u) {
		Server server = u.getServer();
		server.broadcast(new Message("Hey bitches, " + u.getName() + " changed their name to " + newName));
		u.setName(newName);
	}

	@Override
	public void clientRun(Client c) {
		
	}

	@Override
	public void clientRunRecieved(Client c) {
		
	}
}
