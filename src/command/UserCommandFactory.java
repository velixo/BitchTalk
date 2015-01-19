package command;

import java.util.StringTokenizer;

import serverside.Server;
import serverside.User;
import command.serverside.Kick;
import command.serverside.RequestAdmin;
import command.serverside.ServerBossAssBitch;
import command.serverside.ServerCelebrate;
import command.serverside.ServerOpen;
import command.serverside.ServerWhatsGoingOn;
import command.serverside.ServerWoolooloo;
import command.serverside.SetName;

public class UserCommandFactory {
	public final static String SETNAME= "/setname";
	public final static String REQUESTADMIN = "/requestadmin";
	public final static String WOOLOOLOO= "/woolooloo";
	public final static String WHATSGOINGON= "/whatsgoingon";
	public final static String BOSSASSBITCH = "/bossassbitch";
	public final static String KICK = "/kick";
	public final static String OPEN= "/open";
	public final static String CELEBRATE = "/celebrate";

	public final static String BITCHSAYMYNAME = "/bitchsaymyname";
	public final static String GAFFELTRUCK = "/gaffeltruck";
	public final static String RETARDBIRD = "/retardbird";
	
	private Server server;
	private User u;
	
	//TODO: flytta servercommands till usercommands.
	public UserCommandFactory(User u, Server s) {
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
			return new RequestAdmin(server, u, st.nextToken());
			
		case KICK:
			String username = input.replace("/kick ", "");
			return new Kick(server, u, username);

		case WOOLOOLOO:
			return new ServerWoolooloo(server);
			
		case BOSSASSBITCH:
			return new ServerBossAssBitch(server);
		
		case WHATSGOINGON:
			return new ServerWhatsGoingOn(server, u);
			
		case OPEN:
			return new ServerOpen(server);
			
		case CELEBRATE:
			return new ServerCelebrate(server);
		
		default:
			return new NotACommand(u);
		}
	}
}
