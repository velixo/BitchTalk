package command;

import serverside.ServerGui;
import clientSide.ClientGui;

public class NotACommand implements Command {
	private ServerGui s;
	private ClientGui c;
	
	public NotACommand(ServerGui s) {
		this.s = s;
	}
	
	public NotACommand(ClientGui c) {
		this.c = c;
	}

	@Override
	public void run() {
		if (s != null) {
			s.showMessage("Invalid command!");
		}
		if (c != null) {
			c.showMessage("Invalid command");
		}
	}

}
