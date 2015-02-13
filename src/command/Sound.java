package command;

import serverside.User;
import clientSide.Client;

public class Sound implements Command {
	private String soundName;
	
	public Sound(String soundName) {
		this.soundName = soundName;
	}

	@Override
	public void serverRun(User u) {
		u.getServer().broadcast(this);
	}

	@Override
	public void clientRun(Client c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clientRunRecieved(Client c) {
		c.getGui().playSound(soundName);
	}

}
