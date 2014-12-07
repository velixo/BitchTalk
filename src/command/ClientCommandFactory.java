package command;

import java.util.StringTokenizer;

import clientSide.Client;
import clientSide.ClientGui;

import command.clientside.*;

public class ClientCommandFactory {
	public final static String TOGGLEMUTE = "/togglemute";
	public final static String USERJOINED = "/userjoined";
	public final static String USERLEFT = "/userleft";
	public final static String CONNECT = "/connect";
	
	private Client client;
	private ClientGui clientGui;
	
	public ClientCommandFactory(ClientGui cg, Client c) {
		clientGui = cg;
		client = c;
	}
	
	public Command build(String input) {
		
		StringTokenizer st = new StringTokenizer(input);
		
		
		switch (st.nextToken()) {
		case TOGGLEMUTE:
			return new ToggleMute(clientGui);
			
		case USERJOINED:
			if (st.hasMoreTokens())
				return new UserJoined(clientGui, st.nextToken());
			else
				return new NotACommand(clientGui);

		case USERLEFT:
			if (st.hasMoreTokens())
				return new UserLeft(clientGui, st.nextToken());
			else
				return new NotACommand(clientGui);
		
		case CONNECT:
			if(st.hasMoreTokens())
				return new Connect(st.nextToken(),client);
			else
				return new NotACommand(clientGui);
		
		default:
			break;
		}
		return new NotACommand(clientGui);
	}
}
