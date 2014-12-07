package command.clientside;

import clientSide.ClientGui;

import command.Command;

public class UserLeft implements Command {
	private ClientGui c;
	private String username;

	public UserLeft(ClientGui c, String username) {
		this.c = c;
		this.username = username;
	}
	
	@Override
	public void run() {
		c.userLeft(username);
		c.showSilentMessage(username + "decided to be uncool. What a bitch.");
	}

}
