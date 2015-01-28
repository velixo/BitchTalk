package command.clientside;

import java.io.File;
import java.util.StringTokenizer;

import statics.StaticVariables;
import clientSide.Client;
import clientSide.ClientGui;
import command.Command;
import command.NotACommand;

public class ClientCommandFactory {
	public final static String HELP = StaticVariables.HELP;
	public final static String MUTE = StaticVariables.MUTE;
	public final static String UNMUTE = StaticVariables.UNMUTE;
	public final static String CONNECT = StaticVariables.CONNECT;
	
//	public final static String SERVERWOOLOOLOO = StaticVariables.SERVERWOOLOOLOO;
//	public final static String SERVERBOSSASSBITCH = StaticVariables.SERVERBOSSASSBITCH;
//	public final static String SERVERWHATSGOINGON = StaticVariables.SERVERWHATSGOINGON;
//	public final static String SERVERMOVEBITCHGETOUTDAWAY = StaticVariables.SERVERMOVEBITCHGETOUTDAWAY;
//	public final static String SERVEROPEN = StaticVariables.SERVEROPEN;
//	public final static String SERVERCELEBRATE = StaticVariables.SERVERCELEBRATE;
	
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
		
		case CONNECT:
			if (client.connected())
				return new AlreadyConnected(clientGui);
			if(st.hasMoreTokens())
				return new Connect(st.nextToken(),client);
			else
				return new NotACommand(clientGui);
		
		default:
			if(isServerCommand(input)) {
				if(isSound(input)) {
					String soundName = input.replace("/:", "") + ".wav";
					return new ClientSound(clientGui, soundName);
				}
			}
			return new NotACommand(clientGui);
		}
	}
	
	private boolean isServerCommand(String input) {
		return (input.charAt(0)=='/' && input.charAt(1)==':');
	}
	
	private boolean isSound(String input) {
		String soundName = new String(input);
		String adminSoundName = new String(input);
		soundName.replace("/:", "");
		adminSoundName.replace("/:", "admin_");
		
		File soundFolder = new File("res/");
		File[] sounds = soundFolder.listFiles();
		for (File sound : sounds){
			if (sound.getName().equals(soundName + ".wav") || sound.getName().equals(adminSoundName + ".wav"))
				return true;
		}
		return false;
	}
}
