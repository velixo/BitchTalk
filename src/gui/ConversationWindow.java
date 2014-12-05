package gui;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

public class ConversationWindow extends JFrame implements Observer {
	private String windowTitle = "Talking to: ";
	private String selfUsername;
	private String selfIP;
	private Map<String, String> ipUsernameMap;
	
	public ConversationWindow() {
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
