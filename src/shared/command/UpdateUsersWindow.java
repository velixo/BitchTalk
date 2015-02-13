package shared.command;

import java.util.List;

import serverside.Server;
import serverside.User;
import clientSide.Client;

public class UpdateUsersWindow implements Command {
	private static final long serialVersionUID = -3358150254397660668L;
	private List<String> usernames;

	public UpdateUsersWindow(List<String> usernames) {
		this.usernames = usernames;
	}

	@Override
	public void serverRun(User u) {
		Server server = u.getServer();
		server.getServerGui().updateUsersWindow(server.getUsernamesList());
		server.broadcast(this);
	}

	@Override
	public void clientRun(Client c) {

	}

	@Override
	public void clientRunRecieved(Client c) {
		c.getGui().updateUsersWindow(usernames);
	}

}
