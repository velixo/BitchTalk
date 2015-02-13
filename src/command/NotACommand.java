package command;


import serverside.User;
import shared.StaticVariables;
import clientSide.Client;

public class NotACommand implements Command {
	private static final long serialVersionUID = 1L;
	private final String NACMSG = StaticVariables.NOTACOMMANDMESSAGE;
	
	//TODO what the fuck, man

	@Override
	public void serverRun(User u) {
		// TODO Auto-generated method stub
	}

	@Override
	public void clientRun(Client c) {
		c.getGui().showMessage(NACMSG);
	}

	@Override
	public void clientRunRecieved(Client c) {
		// TODO Auto-generated method stub
	}
}
