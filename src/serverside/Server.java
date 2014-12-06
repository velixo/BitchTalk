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
		waitForConnection();
		listenForMessages();
	}
	
	
	private void broadcast(String m){
		for(User u : userList){
			try {
				u.send(m);
			} catch (IOException e) {
				u.closeCrap();
				userList.remove(u);
				gui.showMessage(u.getName() + " decided to be uncool. What a bitch.");
			}
		}
	}
	
	private void waitForConnection(){
		thr.start();
	}
	
	private void listenForMessages(){
		String message = "Bitch, Server's up!";
		do{
			for(User u : userList){
				try {
					message = u.readMessage();
					gui.showMessage(message);
					broadcast(message);
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}while(true);
	}

/********************************************************************************************/
	
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
