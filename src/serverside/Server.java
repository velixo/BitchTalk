package serverside;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	
	private ServerSocket gatekeeper;
	private ServerGui gui;
	private ArrayList<User> userList;
	private Server me = this;
	
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
		waitForConnectionThread.start();
	}
	
	
	void broadcast(String m){
		gui.showMessage(m);
		for(User u : userList){
			try {
				u.send(m);
			} catch (IOException e) {
				wreck(u);
			}
		}
	}
	
	void wreck(User u){
		u.closeCrap();
		userList.remove(u);
		gui.showMessage(u.name() + " decided to be uncool. What a bitch.");
	}

/******************************** THREAD DECLARATIONS *********************************************/
	
	Thread waitForConnectionThread = new Thread() {
		public void run() {
			while(!Thread.currentThread().isInterrupted()) {
				Socket s;
				try {
					s = gatekeeper.accept();
					User u = new User(s,me);
					userList.add(u);
					broadcast(u.name() + " has joined.");
				} catch (IOException e) {
					gui.showMessage("some bitch really sucks at connecting.");
					e.printStackTrace();
				}
			}
		}
	};
}
