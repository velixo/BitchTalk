package command;

import serverside.Server;

import command.serverside.Gaffeltruck;

public class ServerCommandFactory {
	public final static String WOOLOOLOO= "woolooloo";
	public final static String GAFFELTRUCK = "gaffeltruck";
	public final static String BITCHSAYMYNAME = "bitchsaymyname";
	
	private Server server;
	
	
	public ServerCommandFactory (Server s) {
		server = s;
	}
	
	public Command build(String input) {
		switch (input) {
		case WOOLOOLOO:
			
			break;
			
		case GAFFELTRUCK:
			return new Gaffeltruck(server);

		default:
			break;
		}
		return new NotACommand(server.getServerGui());
	}

}