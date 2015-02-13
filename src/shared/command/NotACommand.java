package shared.command;


import serverside.User;
import shared.StaticVariables;
import clientSide.Client;

public class NotACommand implements Command {
	private static final long serialVersionUID = 1L;
	private final String NACMSG = StaticVariables.NOTACOMMANDMESSAGE;

	@Override
	public void serverRun(User u) {
	}

	@Override
	public void clientRun(Client c) {
		c.getGui().showMessage(NACMSG);
	}

	@Override
	public void clientRunRecieved(Client c) {
	}
}
