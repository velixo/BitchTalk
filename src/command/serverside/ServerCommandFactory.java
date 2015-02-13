package command.serverside;

import java.util.StringTokenizer;

import serverside.Server;
import serverside.User;
import shared.StaticVariables;
import command.Command;
import command.NotACommand;

public class ServerCommandFactory {
	public final static String BITCHLIST = StaticVariables .BITCHLIST;
	public final static String SETNAME = StaticVariables.SETNAME;
	public final static String REQUESTADMIN = StaticVariables.REQUESTADMIN;
	public final static String KICK = StaticVariables.KICK;
	public final static String GETIP = StaticVariables.GETIP;
	public final static String GETALIASES = StaticVariables.GETALIASES;
	public final static String ALIAS = StaticVariables.ALIAS;
	public final static String SENDTRUE = StaticVariables.SENDTRUE;
	
	public final static String OLD_WOOLOOLOO = "/woolooloo";
	public final static String OLD_BOSSASSBITCH = "/bossassbitch";
	
	public static Command build(String input, User u) {
		Server server = u.getServer();
		StringTokenizer st = new StringTokenizer(input);
		String username;
		switch (st.nextToken()) {
		case BITCHLIST:
			return new BitchList();
		
		case SETNAME:
			String newUsername = input.replace(SETNAME + " ", "");
			return new SetName(server, u, newUsername);
			
		case REQUESTADMIN:
			String pin = input.replace(REQUESTADMIN + " ", "");
			return new RequestAdmin(pin);
			
		case KICK:
			username = input.replace(KICK + " ", "");
			return new Kick(username);
			
		case GETIP:
			username = input.replace(GETIP + " ", "");
			return new GetIp(username);
			
		case GETALIASES:
			return new GetAliases();
			
		case ALIAS:
			return new Alias(input);
			
		case SENDTRUE:
			return new SendTrue(input);
		
		default:
			return new NotACommand();
		}
	}
}
