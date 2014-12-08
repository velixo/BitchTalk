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
		
		case CONNECT:
			if(st.hasMoreTokens())
				return new Connect(st.nextToken(),client);
			else
				return new NotACommand(clientGui);
		
		default:
			return new NotACommand(clientGui);
		}
	}
}
