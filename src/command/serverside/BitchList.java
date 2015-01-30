package command.serverside;

import java.util.List;

import serverside.Server;
import serverside.User;

import command.Command;

public class BitchList implements Command{
	private Server s;
	private User u;
	
	public BitchList(Server s, User u) {
		this.s = s;
		this.u = u;
	}

	@Override
	public void run() {
		List<String> uNameList = s.getUsernamesList();
		String message = "Deez bitches in da house:";
		for (String uName : uNameList) {
			message += "\n" + uName;
		}
		u.send(message);
	}
	
}
