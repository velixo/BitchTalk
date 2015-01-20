package command.clientside;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import statics.StaticVariables;
import clientSide.ClientGui;

import command.Command;

public class Help implements Command {
	private ClientGui c;
	
	public Help(ClientGui c) {
		this.c = c;
	}

	@Override
	public void run() {
		String help = "\nBitch needed some commands?\n" +
				StaticVariables.HELP + "\n" +
				StaticVariables.CONNECT + "\n" +
				StaticVariables.MUTE + "\n" +
				StaticVariables.UNMUTE + "\n" +
				StaticVariables.SETNAME + "\n";
		List<String> sounds = getNormalSoundNames();
		for(String sound : sounds) {
			help += "/" + sound + "\n";
		}
		c.showSilentMessage(help);
	}
	
	private List<String> getNormalSoundNames() {
		List<String> soundList = new ArrayList<String>();
		File[] sounds = new File("res/").listFiles();
		for(File sound : sounds) {
			if (!sound.getName().contains("other_") && !sound.getName().contains("admin_")) {
				soundList.add(sound.getName().replace(".wav", ""));
			}
		}
		return soundList;
	}

}
