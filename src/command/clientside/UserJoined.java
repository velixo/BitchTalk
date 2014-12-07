package command.clientside;

import clientSide.ClientGui;

import command.Command;

public class UserJoined implements Command {
	private ClientGui c;
	
	public UserJoined(ClientGui c) {
		this.c = c;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
