package command;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import serverside.Server;
import serverside.User;
import statics.StaticVariables;

import command.serverside.Kick;
import command.serverside.RequestAdmin;
import command.serverside.ServerBossAssBitch;
import command.serverside.ServerCelebrate;
import command.serverside.ServerOpen;
import command.serverside.ServerWhatsGoingOn;
import command.serverside.ServerWoolooloo;
import command.serverside.SetName;

public class ServerCommandFactory {
	public final static String SETNAME= StaticVariables.SETNAME;
	public final static String REQUESTADMIN = StaticVariables.REQUESTADMIN;
	public final static String WOOLOOLOO= StaticVariables.WOOLOOLOO;
	public final static String WHATSGOINGON= StaticVariables.WHATSGOINGON;
	public final static String BOSSASSBITCH = StaticVariables.BOSSASSBITCH;
	public final static String KICK = StaticVariables.KICK;
	public final static String OPEN= StaticVariables.OPEN;
	public final static String CELEBRATE = StaticVariables.CELEBRATE;

//	public final static String BITCHSAYMYNAME = "/bitchsaymyname";
//	public final static String GAFFELTRUCK = "/gaffeltruck";
//	public final static String RETARDBIRD = "/retardbird";
	
	private Server server;
	private User u;
	
	//TODO: flytta servercommands till usercommands.
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
