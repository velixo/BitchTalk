package serverside;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	
	ServerSocket gatekeeper;
	ServerGui gui;
	ArrayList<User> userList;
	
	public Server(ServerGui g){
		try {
			gatekeeper = new ServerSocket(9513);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gui = g;
		gui.showMessage("Welcome, bitch king. This realm is yours.");
		userList = new ArrayList<User>();
	}
	
	
	
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
					gui.showMessage("some bitch really sucks at connecting.");
					e.printStackTrace();
				}
			}
		}
	};
}
