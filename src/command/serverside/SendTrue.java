package command.serverside;

import serverside.User;
import shared.StaticVariables;
import shared.command.Command;
import shared.command.Message;
import clientSide.Client;

public class SendTrue implements Command {
	private static final long serialVersionUID = -55731115128113331L;
	private String input;
	
	public SendTrue(String input) {
		this.input = input;
	}

	@Override
	public void serverRun(User u) {
		if (u.isAdmin()) {
			String unaliasedMessage = input.replace(StaticVariables.SENDTRUE + " ", "");
			u.getServer().broadcast(new Message(unaliasedMessage));
		}
	}

	@Override
	public void clientRun(Client c) {
		
	}

	@Override
	public void clientRunRecieved(Client c) {
		
	}
}
