package command.clientside;

import clientSide.ClientGui;

import command.Command;

public class UserJoined implements Command {
	private ClientGui c;
	private String username;
	
	public UserJoined(ClientGui c, String username) {
		this.c = c;
		this.username = username;
	}

	@Override
	public void run() {
		c.userJoined(username);
	}

}
