package command.serverside;

import java.io.IOException;
import java.util.StringTokenizer;

import serverside.Server;
import serverside.User;

import command.Command;

public class Alias implements Command {
	private Server server;
	private User u;
	private String input;
	
	public Alias(Server s, User u, String input) {
		server = s;
		this.u = u;
		this.input = input;
	}

	@Override
	public void run() {
		try {
			StringTokenizer st = new StringTokenizer(input);
			st.nextToken();
			String word = "";
			String alias = "";
			if (st.hasMoreTokens()) {	//input == "/alias *"
				word = st.nextToken();
				if (st.hasMoreTokens()) {	//input == "/alias <word> *"
					alias = st.nextToken();
					server.getAliasizer().set(word, alias);;
				} else	//input != "/alias <word> *"
					u.send("Bitch, you need an alias.");
				
			} else	//input != "/alias *"
				u.send("Bitch, you need a word to be aliased.");
		} catch (IOException e) {
			server.wreck(u);
		}
	}

}
