package command.serverside;

import java.util.StringTokenizer;

import serverside.Server;
import serverside.User;
import clientSide.Client;

import command.Command;
import command.Message;

public class Alias implements Command {
	private static final long serialVersionUID = -8822250186749949747L;
	private String input;
	
	public Alias(String input) {
		this.input = input;
	}

	@Override
	public void serverRun(User u) {
		Server server = u.getServer();
		StringTokenizer st = new StringTokenizer(input);
		st.nextToken();
		if (st.hasMoreTokens()) {	//input == "/alias *"
			String word = st.nextToken();
			if (st.hasMoreTokens()) {	//input == "/alias <word> *"
				String alias = st.nextToken();
				server.getAliasizer().set(word, alias);;
			} else	//input != "/alias <word> *"
				//TODO send is dangerous - fixed, i think
				u.send(new Message("Bitch, you need an alias."));
			
		} else	//input != "/alias *"
			//TODO send is dangerous - fixed, i think
			u.send(new Message("Bitch, you need a word to be aliased."));
	}

	@Override
	public void clientRun(Client c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clientRunRecieved(Client c) {
		// TODO Auto-generated method stub
		
	}

}
