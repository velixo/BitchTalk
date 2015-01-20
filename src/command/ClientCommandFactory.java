package command;

import java.util.StringTokenizer;

import statics.StaticVariables;
import clientSide.Client;
import clientSide.ClientGui;

import command.clientside.AlreadyConnected;
import command.clientside.ClientBossAssBitch;
import command.clientside.ClientCelebrate;
import command.clientside.ClientMoveBitch;
import command.clientside.ClientOpen;
import command.clientside.ClientWhatsGoingOn;
import command.clientside.ClientWoolooloo;
import command.clientside.Connect;
import command.clientside.Help;
import command.clientside.Mute;
import command.clientside.Unmute;

public class ClientCommandFactory {
	public final static String HELP = StaticVariables.HELP;
	public final static String MUTE = StaticVariables.MUTE;
	public final static String UNMUTE = StaticVariables.UNMUTE;
	public final static String CONNECT = StaticVariables.CONNECT;
	
	//Commands that begin with "/:" can only come from server.
	public final static String SERVERWOOLOOLOO = StaticVariables.SERVERWOOLOOLOO;
	public final static String SERVERBOSSASSBITCH = StaticVariables.SERVERBOSSASSBITCH;
	public final static String SERVERWHATSGOINGON = StaticVariables.SERVERWHATSGOINGON;
	public final static String SERVERMOVEBITCHGETOUTDAWAY = StaticVariables.SERVERMOVEBITCHGETOUTDAWAY;
	public final static String SERVEROPEN = StaticVariables.SERVEROPEN;
	public final static String SERVERCELEBRATE = StaticVariables.SERVERCELEBRATE;
	
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
		case HELP:
			return new Help(clientGui);
		
		case MUTE:
			return new Mute(clientGui);
			
		case UNMUTE:
			return new Unmute(clientGui);
			
		case SERVERWOOLOOLOO:
			return new ClientWoolooloo(clientGui);
			
		case SERVERBOSSASSBITCH:
			return new ClientBossAssBitch(clientGui);
			
		case SERVERWHATSGOINGON:
			return new ClientWhatsGoingOn(clientGui);
			
		case SERVERMOVEBITCHGETOUTDAWAY:
			return new ClientMoveBitch(clientGui);
		
		case SERVEROPEN:
			return new ClientOpen(clientGui);
		
		case SERVERCELEBRATE:
			return new ClientCelebrate(clientGui);
		
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
