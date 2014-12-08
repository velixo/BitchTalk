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
	private Server me = this;
	
	public Server(ServerGui g){
		try {
			gatekeeper = new ServerSocket(9513);
			gui = g;
			gui.showMessage("Welcome, bitch king. " + InetAddress.getLocalHost().getHostAddress() + " is yours.");
			userList = new ArrayList<User>();
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

/******************************** THREAD DECLARATIONS *********************************************/
	
	Thread waitForConnectionThread = new Thread() {
		public void run() {
			while(!Thread.currentThread().isInterrupted()) {
				Socket s;
				try {
					//TODO add user to chatroom window
					s = gatekeeper.accept();
					User u = new User(s,me);
					userList.add(u);
					broadcast(u.name() + " has joined.");
					updateUsersWindow();
					broadcastUsernameList();
				} catch (IOException e) {
					gui.showMessage("some bitch really sucks at connecting.");
					e.printStackTrace();
				}
			}
		}
	};
}
