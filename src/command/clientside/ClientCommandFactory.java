package command.clientside;

import java.io.File;
import java.util.StringTokenizer;

import shared.StaticVariables;
import command.Command;
import command.Message;
import command.NotACommand;
import command.Sound;
import command.UnrecognizedCommand;

public class ClientCommandFactory {
	public final static String HELP = StaticVariables.HELP;
	public final static String MUTE = StaticVariables.MUTE;
	public final static String UNMUTE = StaticVariables.UNMUTE;
	public final static String CONNECT = StaticVariables.CONNECT;
	
//	public boolean canBuild(String in){
//		return !(build(in) instanceof NotACommand);
//	}
	
	public static String help(){
		return "type /connect <ip-address> to connect, bitch.";
	}
	
	public static Command build(String input) {
		
		StringTokenizer st = new StringTokenizer(input);
		
		
		switch (st.nextToken()) {
		case HELP:
			return new Help();
		
		case MUTE:
			return new Mute();
			
		case UNMUTE:
			return new Unmute();
		
		case CONNECT:
			if(st.hasMoreTokens())
				return new Connect(st.nextToken());
			else
				return new NotACommand();
		
		default:
			if(isSound(input)) {
				String soundName = input.replace("/:", "");
				return new Sound(soundName);
//				return new ClientSound(clientGui, soundName);
			} else if(isPossibleCommand(input))
				return new UnrecognizedCommand(input);
			return new Message(input);
//			return new NotACommand();
		}
	}
	
	private static boolean isPossibleCommand(String input) {
		return input.charAt(0) == '/';
	}
	
	private static boolean isSound(String input) {
		System.out.println("ClientCommandFactory.isSound()");
		String normalSoundName = input.replace("/", "");
		String adminSoundName = input.replace("/", "admin_");
		String otherSoundName = input.replace("/", "other_");
		String hiddenSoundName = input.replace("/", "hidden_");
		
		File soundFolder = new File("res/");
		File[] sounds = soundFolder.listFiles();
		for (File sound : sounds){
			String sName = sound.getName();
			if(sName.equals(normalSoundName + ".wav")
			|| sName.equals(adminSoundName + ".wav")
			|| sName.equals(otherSoundName + ".wav")
			|| sName.equals(hiddenSoundName + ".wav"))
				return true;
		}
		return false;
	}
}
