package command;

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
		System.out.println("Sound.serverRun(), soundName == " + soundName);
		server.getServerGui().showMessage("BARK: " + soundName);
	}

	@Override
	public void clientRun(Client c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clientRunRecieved(Client c) {
		System.out.println("Sound.clientRunRecieved()");
		c.getGui().playSound(soundName);
	}

}
