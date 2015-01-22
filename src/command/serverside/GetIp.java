package command.serverside;

import java.io.IOException;

import serverside.Server;
import serverside.User;
import command.Command;

public class GetIp implements Command {
	private Server s;
	private User u;
	private String username;
	
	public GetIp(Server s, User u, String username) {
		this.s = s;
		this.u = u;
		this.username = username;
	}

	@Override
	public void run() {
		try {
			if (u.isAdmin()) {
				String ip = s.getIp(username);
				u.send("Bitch " + username + "'s IP is " + ip);
			}
		} catch (IOException e) {
			e.printStackTrace();
			s.wreck(u);	//check if this is alright
		}
	}

}
