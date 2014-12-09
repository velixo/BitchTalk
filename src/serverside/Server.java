package serverside;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
	
	private ServerSocket gatekeeper;
	private ServerGui gui;
	private ArrayList<User> userList;
	private ArrayList<InetAddress> blackList;
	private Server me = this;
	private String adminPin;
	
	public Server(ServerGui g){
		try {
			gatekeeper = new ServerSocket(9513);
			gui = g;
			gui.showMessage("Welcome, bitch king. " + InetAddress.getLocalHost().getHostAddress() + " is yours.");
			userList = new ArrayList<User>();
			adminPin = randomizePin();
			blackList = new ArrayList<InetAddress>();
			waitForConnectionThread.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void broadcast(String m){
		gui.showMessage(m);
		for(User u : userList){
			try {
				u.send(m);
			} catch (IOException e) {
				wreck(u);
			}
		}
	}
	public void broadcastUsernameList() {
		List<String> usernames = new ArrayList<String>();
		for (User u : userList) {
			usernames.add(u.name());
		}
		for(User u : userList) {
			try {
				u.send(usernames);
			} catch (IOException e) {
				wreck(u);
			}
		}
	}
	
	void wreck(User u){
		u.closeCrap();
		userList.remove(u);
		gui.showMessage(u.name() + " decided to be uncool. What a bitch.");	//TODO broadcast this?
		updateUsersWindow();
		broadcastUsernameList();
	}
	
	private void updateUsersWindow() {
		List<String> usernames = new ArrayList<String>();
		for (User u : userList) {
			usernames.add(u.name());
		}
		gui.updateUsersWindow(usernames);
	}
	
	public ServerGui getServerGui() {
		return gui;
	}
	
	public void ban(String username) {
		for (User u : userList) {
			if (u.name().equals(username)) {
				wreck(u);
				blackList.add(u.getInetAddress());
			}
		}
	}
	
	public boolean pinIsCorrect(String pinGuess) {
		return adminPin.equals(pinGuess);
	}
	
	private String randomizePin() {
		//TODO implement randomizePin() correctly
		String newPin = "abc123";
		gui.showMessage("Admin access pin: " + newPin);
		return newPin;
	}

/******************************** THREAD DECLARATIONS *********************************************/
	
	Thread waitForConnectionThread = new Thread() {
		public void run() {
			while(!Thread.currentThread().isInterrupted()) {
				Socket s;
				try {
					//TODO add user to chatroom window
					s = gatekeeper.accept();
					if (!blackList.contains(s.getInetAddress())) {
						User u = new User(s,me);
						userList.add(u);
						broadcast(u.name() + " has joined.");
						updateUsersWindow();
						broadcastUsernameList();
					} else {
						gui.showMessage(s.getInetAddress().toString() + " is banned and he tried to join. Lol, who dis bitch think he is?");
						s.close();
					}
				} catch (IOException e) {
					gui.showMessage("some bitch really sucks at connecting.");
					e.printStackTrace();
				}
			}
		}
	};
	
}
