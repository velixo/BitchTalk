package command;

import clientSide.ClientGui;

import command.clientside.ToggleMute;
import command.clientside.UserJoined;
import command.clientside.UserLeft;

public class ClientCommandFactory {
	public final static String TOGGLEMUTE = "/togglemute";
	public final static String USERJOINED = "/userjoined";
	public final static String USERLEFT = "/userleft";
	
	private ClientGui clientGui;
	
	public ClientCommandFactory(ClientGui c) {
		clientGui = c;
	}
	
	public Command build(String input) {
		switch (input) {
		case TOGGLEMUTE:
			return new ToggleMute(clientGui);
			
		case USERJOINED:
			return new UserJoined(clientGui);

		case USERLEFT:
			return new UserLeft(clientGui);
			
		default:
			break;
		}
		return new NotACommand(clientGui);
	}
}
