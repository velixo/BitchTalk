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
//		waitForConnection();
		waitForConnectionThread.start();
		listenForMessagesThread.start();
//		listenForMessages();
	}
	
	
	private void broadcast(String m){
		gui.showMessage(m);
		for(User u : userList){
			try {
				u.send(m);
			} catch (IOException e) {
				wreck(u);
			}
		}
	}
	
	private void waitForConnection(){
		waitForConnectionThread.start();
	}
	
	private void listenForMessages(){
		String message = "Bitch, Server's up!";
		do{
			for(User u : userList){
				try {
					if(u.hasMessage()){
						message = u.readMessage();
						broadcast(message);
					}
				} catch (ClassNotFoundException | IOException e) {
					wreck(u);
				}
				
			}
		}while(true);
	}
	
	private void wreck(User u){
		u.closeCrap();
		userList.remove(u);
		gui.showMessage(u.getName() + " decided to be uncool. What a bitch.");
	}

/******************************** THREAD DECLARATIONS *********************************************/
	
	Thread waitForConnectionThread = new Thread() {
		public void run() {
			while(!Thread.currentThread().isInterrupted()) {
				Socket s;
				try {
					s = gatekeeper.accept();
					User u = new User(s);
					userList.add(u);
					broadcast(u.getName() + " has joined.");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					gui.showMessage("some bitch really sucks at connecting.");
					e.printStackTrace();
				}
			}
		}
	};
	
	Thread listenForMessagesThread = new Thread() {
		public void run() {
			String message = "Bitch, Server's up!";
			System.out.println("bitch im listenin");
//			while(!Thread.currentThread().isInterrupted()) {
			while(true) {
				for(User u : userList){
					System.out.println("in da for, bietch");
					try {
						System.out.println("bitch got summin to say?");
//						if(u.hasMessage()){
							System.out.println("dis bitch got shit to say");
							message = u.readMessage();
							broadcast(message);
//						}
					} catch (ClassNotFoundException | IOException e) {
						wreck(u);
					}
					
				}
			}
		}
	};
}
