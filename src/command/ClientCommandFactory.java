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
	public final static String CLIENTWOOLOOLOO = StaticVariables.CLIENTWOOLOOLOO;
	public final static String CLIENTBOSSASSBITCH = StaticVariables.CLIENTBOSSASSBITCH;
	public final static String CLIENTWHATSGOINGON = StaticVariables.CLIENTWHATSGOINGON;
	public final static String CLIENTMOVEBITCHGETOUTDAWAY = StaticVariables.CLIENTMOVEBITCHGETOUTDAWAY;
	public final static String CLIENTOPEN = StaticVariables.CLIENTOPEN;
	public final static String CLIENTCELEBRATE = StaticVariables.CLIENTCELEBRATE;
	
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
			
		case CLIENTWOOLOOLOO:
			return new ClientWoolooloo(clientGui);
			
		case CLIENTBOSSASSBITCH:
			return new ClientBossAssBitch(clientGui);
			
		case CLIENTWHATSGOINGON:
			return new ClientWhatsGoingOn(clientGui);
			
		case CLIENTMOVEBITCHGETOUTDAWAY:
			return new ClientMoveBitch(clientGui);
		
		case CLIENTOPEN:
			return new ClientOpen(clientGui);
		
		case CLIENTCELEBRATE:
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
