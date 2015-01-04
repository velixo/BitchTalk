package command;

import java.util.StringTokenizer;

import serverside.Server;
import serverside.User;

import command.serverside.SetName;
import command.serverside.Kick;
import command.serverside.RequestAdmin;
import command.serverside.ServerBossAssBitch;
import command.serverside.ServerWhatsGoingOn;
import command.serverside.ServerWoolooloo;

public class UserCommandFactory {
	public final static String SETNAME= "/setname";
	public final static String REQUESTADMIN = "/requestadmin";
	public final static String WOOLOOLOO= "/woolooloo";
	public final static String WHATSGOINGON= "/whatsgoingon";
	public final static String BOSSASSBITCH = "/bossassbitch";
	public final static String KICK = "/kick";

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
			return new SetName(u, st.nextToken());
			
		case REQUESTADMIN:
			return new RequestAdmin(server, u, st.nextToken());
			
		case KICK:
			return new Kick(server, u, st.nextToken());

		case WOOLOOLOO:
			return new ServerWoolooloo(server);
			
		case BOSSASSBITCH:
			return new ServerBossAssBitch(server);
		
		case WHATSGOINGON:
			return new ServerWhatsGoingOn(server, u);
		
		default:
			return new NotACommand(u);
		}
	}
}
