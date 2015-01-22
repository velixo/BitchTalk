package command;

import java.io.IOException;
import java.util.StringTokenizer;

import serverside.Server;
import serverside.User;
import statics.StaticVariables;

import command.serverside.BitchList;
import command.serverside.GetIp;
import command.serverside.Kick;
import command.serverside.RequestAdmin;
import command.serverside.ServerSound;
import command.serverside.SetName;

public class ServerCommandFactory {
	public final static String BITCHLIST = StaticVariables .BITCHLIST;
	public final static String SETNAME = StaticVariables.SETNAME;
	public final static String REQUESTADMIN = StaticVariables.REQUESTADMIN;
	public final static String KICK = StaticVariables.KICK;
	public final static String GETIP = StaticVariables.GETIP;
	public final static String OLD_WOOLOOLOO = "/woolooloo";
	public final static String OLD_BOSSASSBITCH = "/bossassbitch";
	
	private Server server;
	private User u;
	
	public ServerCommandFactory(User u, Server s) {
		this.u = u;
		server = s;
	}
	
	public Command build(String input) {
		StringTokenizer st = new StringTokenizer(input);
		String username;
		switch (st.nextToken()) {
		case BITCHLIST:
			return new BitchList(server, u);
		
		case SETNAME:
			String newUsername = input.replace(SETNAME + " ", "");
			return new SetName(server, u, newUsername);
			
		case REQUESTADMIN:
			String pin = input.replace(REQUESTADMIN + " ", "");
			return new RequestAdmin(server, u, pin);
			
		case KICK:
			username = input.replace(KICK + " ", "");
			return new Kick(server, u, username);
			
		case GETIP:
			username = input.replace(GETIP + " ", "");
			return new GetIp(server, u, username);
		
		default:
			if(isOldCommand(input)) {
				try {
					u.send("Bitch, you need an update. The fucking file is where you found the last one, bitch.");
				} catch (IOException e) {
					server.wreck(u);
				}
			} else if(isAdminSound(input) && u.isAdmin()) {
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
	
	private boolean isOldCommand(String input) {	
		return input.equals(OLD_WOOLOOLOO) || input.equals(OLD_BOSSASSBITCH);
	}
}
