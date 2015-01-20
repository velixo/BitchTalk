package command.serverside;

import serverside.Server;

import command.Command;

public class ServerSound implements Command {
	private Server s;
	private String soundName;
	
	public ServerSound(Server s, String soundName) {
		this.s = s;
		this.soundName = soundName;
	}

	@Override
	public void run() {
		s.broadcast(soundName);
	}

}
