package command.clientside;

import statics.StaticVariables;
import clientSide.Client;
import clientSide.ClientGui;

import command.Command;

public class Help implements Command {
	private ClientGui c;
	
	public Help(ClientGui c) {
		this.c = c;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String help = "\nBitch needed some commands?\n" +
				StaticVariables.HELP + "\n" +
				StaticVariables.CONNECT + "\n" +
				StaticVariables.MUTE + "\n" +
				StaticVariables.UNMUTE + "\n" +
				StaticVariables.SETNAME + "\n" +
				StaticVariables.WOOLOOLOO + "\n" +
				StaticVariables.BOSSASSBITCH + "\n" +
				StaticVariables.OPEN + "\n" +
				StaticVariables.CELEBRATE + "\n";
		c.showSilentMessage(help);
	}

}
