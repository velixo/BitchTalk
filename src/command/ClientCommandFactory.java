package command;

import clientSide.ClientGui;

import command.clientside.ToggleMute;

public class ClientCommandFactory {
	public final static String TOGGLEMUTE = "/togglemute";
	
	private ClientGui clientGui;
	
	public ClientCommandFactory(ClientGui c) {
		clientGui = c;
	}
	
	public Command build(String input) {
		switch (input) {
		case TOGGLEMUTE:
			return new ToggleMute(clientGui);

		default:
			break;
		}
		return new NotACommand(clientGui);
	}
}
