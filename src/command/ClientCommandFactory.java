package command;

import java.util.StringTokenizer;

import clientSide.Client;
import clientSide.ClientGui;
import command.clientside.*;

public class ClientCommandFactory {
	public final static String TOGGLEMUTE = "/togglemute";
	public final static String MUTE = "/mute";
	public final static String UNMUTE = "/unmute";
	public final static String CONNECT = "/connect";
	
	//Commands that begin with "/:" can only come from server.
	public final static String CLIENTWOOLOOLOO = "/:woolooloo";
	public final static String CLIENTBOSSASSBITCH = "/:bossassbitch";
	public final static String CLIENTWHATSGOINGON = "/:whatsgoingon";
	public final static String MOVEBITCHGETOUTDAWAY = "/:movebitchgetoutdaway";
	
	private Client client;
	private ClientGui clientGui;
	
	public ClientCommandFactory(ClientGui cg, Client c) {
		clientGui = cg;
		client = c;
		
	}
	public boolean canBuild(String in){
		return !(build(in) instanceof NotACommand);
	}
	public String help(){
		return "type /connect <ip-address> to connect, bitch.";
	}
	
	public Command build(String input) {
		
		StringTokenizer st = new StringTokenizer(input);
		
		
		switch (st.nextToken()) {
		case TOGGLEMUTE:
			return new ToggleMute(clientGui);
			
		case MUTE:
			return new Mute(clientGui);
			
		case UNMUTE:
			return new Unmute(clientGui);
			
		case CLIENTWOOLOOLOO:
			return new ClientWoolooloo(clientGui);
			
		case CLIENTBOSSASSBITCH:
			return new ClientBossAssBitch(clientGui);
			
		case CLIENTWHATSGOINGON:
			return new ClientWhatsGoingOn(clientGui);
			
		case MOVEBITCHGETOUTDAWAY:
			return new MoveBitch(clientGui);
		
		case CONNECT:
			if (client.connected())
				return new AlreadyConnected(clientGui);
			if(st.hasMoreTokens())
				return new Connect(st.nextToken(),client);
			else
				return new NotACommand(clientGui);
		
		default:
			return new NotACommand(clientGui);
		}
	}
	
}
