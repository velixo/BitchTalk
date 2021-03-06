package command.clientside;

import java.io.File;
import java.util.StringTokenizer;

import shared.StaticVariables;
import shared.command.Command;
import shared.command.Message;
import shared.command.NotACommand;
import shared.command.Sound;
import shared.command.UnrecognizedCommand;

public class ClientCommandFactory {
	public final static String HELP = StaticVariables.HELP;
	public final static String MUTE = StaticVariables.MUTE;
	public final static String UNMUTE = StaticVariables.UNMUTE;
	public final static String CONNECT = StaticVariables.CONNECT;
	private final static String NOT_A_SOUND = "NOT_A_SOUND";
	
	public static String help(){
		return "type /connect <ip-address> to connect and /help for help, bitch.";
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
			String soundName = getSoundName(input);
			if(!soundName.equals(NOT_A_SOUND)) {
				return new Sound(soundName);
			} else if(isPossibleCommand(input))
				return new UnrecognizedCommand(input);
			return new Message(input);
		}
	}
	
	private static boolean isPossibleCommand(String input) {
		return input.charAt(0) == '/';
	}
	
	
	private static String getSoundName(String input) {
		String normalSoundName = input.replace("/", "");
		String adminSoundName = input.replace("/", "admin_");
		String otherSoundName = input.replace("/", "other_");
		String hiddenSoundName = input.replace("/", "hidden_");
		
		String[] soundNames = {normalSoundName, adminSoundName, otherSoundName, hiddenSoundName};
		for (String soundName : soundNames) {
			if(soundExists(soundName))
				return soundName;
		}
		
		return NOT_A_SOUND;
	}
	
	private static boolean soundExists(String soundName) {
		File soundFolder = new File("res/");
		File[] sounds = soundFolder.listFiles();
		
		for (File sound : sounds){
			if(sound.getName().equals(soundName + ".wav"))
				return true;
		}
		return false;
	}
}
