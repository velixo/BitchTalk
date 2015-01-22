package command.serverside;

import serverside.Server;
import serverside.User;

import command.Command;

public class SetName implements Command {
	private Server s;
	private User usr;
	private String newName;
	
	public SetName(Server s, User u, String n){
		this.s = s;
		usr = u;
		newName = n;
	}
	
	@Override
	public void run() {
		s.broadcast("Hey bitches, " + usr.name() + " changed their name to " + newName);
		usr.setName(newName);
	}

}
