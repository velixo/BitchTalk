package shared;

public abstract class StaticVariables {
	/** Variables used when a command is sent from the server to a client*/
	public final static String SERVER_MOVEBITCH = "other_movebitch";
	public final static String SERVER_LEAVECHAT = "other_leavechat";
	public final static String SERVER_JOINCHAT = "other_joinchat";
	public static final String INCHARGE = "other_incharge";
	
	/** Variables used when a command is sent from a client to the server*/
	public final static String HELP = "/help";
	public final static String MUTE = "/mute";
	public final static String UNMUTE = "/unmute";
	public final static String CONNECT = "/connect";
	public final static String SETNAME= "/setname";
	public final static String REQUESTADMIN = "/requestadmin";
	public final static String KICK = "/kick";
	public final static String BAN = "/ban";
	public final static String BITCHLIST = "/bitchlist";
	public final static String GETIP = "/getip";
	public final static String GETALIASES = "/getaliases";
	public final static String ALIAS = "/alias";
	public final static String SENDTRUE = "/sendtrue";
	public final static String DISCONNECT = "/disconnect";
	
	public final static String NOTACOMMANDMESSAGE = "Bitch, thats not a command!";
}
