package command.serverside;

import serverside.Server;
import serverside.User;
import clientSide.Client;

import command.Command;
import command.Message;

public class RequestAdmin implements Command {
	private static final long serialVersionUID = -4380450940661031447L;
	private String pin;
	
	public RequestAdmin(String pin) {
		this.pin = pin;
	}

	@Override
	public void serverRun(User u) {
		Server server = u.getServer();
		if (server.pinIsCorrect(pin)) {
			u.setAdmin(true);
			//TODO send is dangerous - fixed, i think
			u.send(new Message("Congrats, bitch. You're now an admin. Big fucking whoop."));
		} else {
			//TODO send is dangerous - fixed, i think
			u.send(new Message("Incorrect pin, bitch. You sure you're ready to be an admin?"));
		};
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
