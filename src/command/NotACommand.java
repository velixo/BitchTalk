package command;

import serverside.ServerGui;
import serverside.User;
import statics.StaticVariables;
import clientSide.ClientGui;

public class NotACommand implements Command {
	private ServerGui s;
	private ClientGui c;
	private User u;
	private final String NACMSG = StaticVariables.NOTACOMMANDMESSAGE;
	
	//TODO what the fuck, man
	public NotACommand(ServerGui s) {
		this.s = s;
	}
	
	public NotACommand(ClientGui c) {
		this.c = c;
	}
	
	public NotACommand(User u) {
		this.u = u;
	}

	@Override
	public void run() {
		//TODO: snygga till.
		if (s != null)
			s.showMessage(NACMSG); //TODO i mean, what the actual fuck, man
		if (c != null)
			c.showMessage(NACMSG);
		if (u != null) {
			u.send(NACMSG);
		}
	}

}
