package command;

import serverside.Server;

import command.serverside.Gaffeltruck;
import command.serverside.ServerWoolooloo;

public class ServerCommandFactory {
	public final static String WOOLOOLOO= "/woolooloo";
	public final static String GAFFELTRUCK = "/gaffeltruck";
	public final static String BITCHSAYMYNAME = "bitchsaymyname";
	public final static String RETARDBIRD = "/retardbird";
	
	private Server server;
	
	
	public ServerCommandFactory (Server s) {
		server = s;
	}
	
	public Command build(String input) {
		switch (input) {
		case WOOLOOLOO:
			return new ServerWoolooloo(server);
			
		case GAFFELTRUCK:
			return new Gaffeltruck(server);

		default:
			break;
		}
		return new NotACommand(server.getServerGui());
	}
	
	public static boolean isAServerCommand(String input) {
		switch (input) {
		case WOOLOOLOO:
			return true;

		default:
			return false;
		}
	}

}
