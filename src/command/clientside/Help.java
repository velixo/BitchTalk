package command.clientside;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import serverside.User;
import shared.StaticVariables;
import clientSide.Client;

import command.Command;

public class Help implements Command {
	private static final long serialVersionUID = 5833972601159022991L;

	@Override
	public void clientRun(Client c) {
		String help = "\nBitch needed some commands?\n" +
				StaticVariables.HELP + "\n" +
				StaticVariables.CONNECT + "\n" +
				StaticVariables.MUTE + "\n" +
				StaticVariables.UNMUTE + "\n" +
				StaticVariables.BITCHLIST + "\n" +
				StaticVariables.SETNAME + "\n";
		List<String> sounds = getNormalSoundNames();
		for(String sound : sounds) {
			help += "/" + sound + "\n";
		}
		c.getGui().showSilentMessage(help);
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

	@Override
	public void serverRun(User u) {
		
	}

	@Override
	public void clientRunRecieved(Client c) {
		
	}

}
