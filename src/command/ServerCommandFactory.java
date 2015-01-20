package command;

import java.util.StringTokenizer;

import serverside.Server;
import serverside.User;
import statics.StaticVariables;

import command.serverside.Kick;
import command.serverside.RequestAdmin;
import command.serverside.ServerSound;
import command.serverside.SetName;

public class ServerCommandFactory {
	public final static String SETNAME= StaticVariables.SETNAME;
	public final static String REQUESTADMIN = StaticVariables.REQUESTADMIN;
	public final static String KICK = StaticVariables.KICK;
	
	private Server server;
	private User u;
	
	public ServerCommandFactory(User u, Server s) {
		this.u = u;
		server = s;
	}
	
	public Command build(String input) {
		StringTokenizer st = new StringTokenizer(input);
		switch (st.nextToken()) {
		case SETNAME:
			String newUsername = input.replace(SETNAME + " ", "");
			return new SetName(u, newUsername);
			
		case REQUESTADMIN:
			String pin = input.replace(StaticVariables.REQUESTADMIN + " ", "");
			return new RequestAdmin(server, u, pin);
			
		case KICK:
			String username = input.replace(StaticVariables.KICK + " ", "");
			return new Kick(server, u, username);
		
		default:
			if(isAdminSound(input) && u.isAdmin()) {
				return new ServerSound(server, encodeSoundString(input));
			} else if(isNormalSound(input)) {
				return new ServerSound(server, encodeSoundString(input));
			}
			return new NotACommand(u);
		}
	}
	
	private static boolean isAdminSound(String input) {
		return (input.charAt(1)==':' && input.charAt(2)=='a' && input.charAt(3)==':');
	}
	
	private static boolean isNormalSound(String input) {
		return (input.charAt(1)==':' && input.charAt(2)=='s' && input.charAt(3)==':');
	}
	
	private static String encodeSoundString(String input) {
		if(isAdminSound(input))
			return input.replace("/:a:", "/:admin_");
		else
			return input.replace("/:s:", "/:" );
	}
}
