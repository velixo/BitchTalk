package shared.command;

import serverside.Server;
import serverside.User;
import clientSide.Client;

public class Sound implements Command {
	private static final long serialVersionUID = 5340041150429851292L;
	private String soundName;
	
	public Sound(String soundName) {
		this.soundName = soundName;
	}

	@Override
	public void serverRun(User u) {
		Server server = u.getServer();
		server.broadcast(this);
		server.getServerGui().showMessage(u.getName() + " BE BARKIN: " + soundName);
	}

	@Override
	public void clientRun(Client c) {
		c.send(this);
	}

	@Override
	public void clientRunRecieved(Client c) {
		c.getGui().playSound(soundName);
	}
}
