package serverside;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerBitch {
	
	ServerSocket gatekeeper;
	ServerGui gui;
	ArrayList<User> userList = new ArrayList<User>();
	
	
	private void waitForConnection(){
		thr.start();
	}

	
	
	
	
	
	
	
	
	
	
	
	Thread thr = new Thread() {
		public void run() {
			while(!Thread.currentThread().isInterrupted()) {
				Socket s;
				try {
					s = gatekeeper.accept();
					userList.add(new User(s));
					gui.showMessage("some bitch has joined.");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					gui.showMessage("någon är för dålig för att connecta.");
					e.printStackTrace();
				}
			}
		}
	};
}
