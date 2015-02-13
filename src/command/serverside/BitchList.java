package command.serverside;

import java.util.List;

import serverside.Server;
import serverside.User;
import shared.command.Command;
import shared.command.Message;
import clientSide.Client;

public class BitchList implements Command{
	private static final long serialVersionUID = 1023418125074564655L;

	@Override
	public void serverRun(User u) {
		Server server = u.getServer();
		List<String> uNameList = server.getUsernamesList();
		String message = "Deez bitches in da hoose:";
		for (String uName : uNameList) {
			message += "\n" + uName;
		}
		//TODO send is dangerous - fixed, i think
		u.send(new Message(message));
	}

	@Override
	public void clientRun(Client c) {
		
	}

	@Override
	public void clientRunRecieved(Client c) {
		
	}

}
