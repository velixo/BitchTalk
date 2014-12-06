package command;

public class CommandFactory {
	public final static String SETNAME= "setname";
	
	public final static String WOOLOOLOO= "woolooloo";
	public final static String GAFFELTRUCK = "gaffeltruck";

	public CommandFactory() {
		
	}
	
	public Command build(String input) {
		switch (input) {

		case SETNAME:
			break;
		
		case GAFFELTRUCK:
			break;
			
		case WOOLOOLOO:
			break;
			
		default:
			break;
		}
		return null;
	}
}
