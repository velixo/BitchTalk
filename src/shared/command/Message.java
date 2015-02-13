package shared.command;

import serverside.Aliasizer;
import serverside.User;
import clientSide.Client;


public class Message implements Command {
	private static final long serialVersionUID = -8662147529730890787L;
	private String text;
	
	public Message(String s) {
		this.text = s;
	}

	@Override
	public void serverRun(User u) {
		Aliasizer aliasizer = u.getServer().getAliasizer();
		text = aliasizer.aliasify(u.getName() + ": " + text);
		u.getServer().broadcast(this);
	}


	@Override
	public void clientRunRecieved(Client c) {
		c.getGui().showMessage(text);
	}


	@Override
	public void clientRun(Client c) {
		c.send(this);
	}
}
