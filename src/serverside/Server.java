package serverside;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import statics.StaticVariables;

public class Server {
	
	private ServerSocket gatekeeper;
	private ServerGui gui;
	private ArrayList<User> userList;
	private ArrayList<String> blackList;
	private Server me = this;
	private String adminPin;
	
	public Server(ServerGui g){
		try {
			gatekeeper = new ServerSocket(9513);
			gui = g;
			gui.showMessage("Welcome, bitch king. " + InetAddress.getLocalHost().getHostAddress() + " is yours.");
			userList = new ArrayList<User>();
			adminPin = randomizePin();
			gui.showMessage("Admin access pin: " + adminPin);
			blackList = new ArrayList<String>();
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
//		List<String> usernames = new ArrayList<String>();
//		for (User u : userList) {
//			usernames.add(u.name());
//		}
		List<String> usernames = getUsernamesList();
		for(User u : userList) {
			try {
				u.sendUserList(usernames);
			} catch (IOException e) {
				wreck(u);
			}
		}
	}
	
	public List<String> getUsernamesList() {
		List<String> usernames = new ArrayList<String>();
		for (User u : userList) {
			usernames.add(u.name());
		}
		return usernames;
	}
	
	public void wreck(User u) {
		u.closeCrap();
		userList.remove(u);
		broadcast(u.name() + " decided to be uncool. What a bitch.");
		updateUsersWindow();
		broadcastUsernameList();
	}
	
	public void kick(User kicker, String username) {
		for (User u : userList) {
			if (u.name().equals(username)) {
				broadcast(StaticVariables.SERVERMOVEBITCHGETOUTDAWAY);
				u.closeCrap();
				userList.remove(u);
				broadcast(username + ", fuck off bitch.");
				updateUsersWindow();
				broadcastUsernameList();
				return;
			}
		}
		try {
			kicker.send("That bitch isn't in this chat, yo.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void updateUsersWindow() {
		List<String> usernames = new ArrayList<String>();
		for (User u : userList) {
			usernames.add(u.name());
		}
		gui.updateUsersWindow(usernames);
	}
	
	public String getIp(String username) {
		for (User u : userList) {
			if (u.name().equals(username)) {
				return u.getInetAddress().toString().replace("/", "");
			}
		}
		return null;
	}
	
	public ServerGui getServerGui() {
		return gui;
	}
	
	public void ban(User unbanner, String username) {
		for (User u : userList) {
			if (u.name().equals(username)) {
				wreck(u);
				blackList.add(u.getInetAddress().toString());
			}
		}
	}
	
	public void unban(User unbanner, String ip) {
		try {
			if (blackList.contains(ip)) {
				blackList.remove(ip);
					unbanner.send(ip + " was removed from the ban list. Hope the bitch keeps his manners this time.");
			} else {
				unbanner.send(ip + " isn't banned.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendBannedList(User u) {
		try {
			StringBuilder message = new StringBuilder("The following IP's are banned: \n");
			for (String ip : blackList) {
				message.append(ip + "\n");
			}
			u.send(message.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean pinIsCorrect(String pinGuess) {
		return adminPin.equals(pinGuess);
	}
	
	private String randomizePin() {
		int pinLength = 6;
		String possChars = "0123456789abcdefghjklmnopqrstuvwxyz";
		Random rand = new Random();
		StringBuilder newPin = new StringBuilder();
		for (int i = 0; i < pinLength; i++) {
			char c = possChars.charAt(rand.nextInt(possChars.length()));
			newPin.append(c);
		}
		return newPin.toString();
	}

/******************************** THREAD DECLARATIONS *********************************************/
	
	Thread waitForConnectionThread = new Thread() {
		public void run() {
			while(!Thread.currentThread().isInterrupted()) {
				Socket s;
				try {
					s = gatekeeper.accept();
					if (!blackList.contains(s.getInetAddress().toString())) {
						User u = new User(s,me);
						userList.add(u);
						broadcast(u.name() + " has joined.");
						u.send("Bitch, we've updated the app. New version's in the facebook group. In the new version there are new sounds and commands. Type /help to see them, bitch.");
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
