package serverside;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import shared.StaticVariables;
import shared.command.Command;
import shared.command.Disconnect;
import shared.command.Message;
import shared.command.Sound;
import shared.command.UpdateUsersWindow;
import command.serverside.BitchList;

public class Server {
	private ServerSocket gatekeeper;
	private ServerGui gui;
	private ArrayList<User> userList;
	private Set<User> usersToBeWrecked;
	private ArrayList<String> blackList;
	private Aliasizer aliasizer;
	private Server me = this;
	private String adminPin;

	public Server(ServerGui g) {
		try {
			gatekeeper = new ServerSocket(9513);
			gui = g;
			gui.showMessage("Welcome, bitch king. "
					+ InetAddress.getLocalHost().getHostAddress()
					+ " is yours.");
			userList = new ArrayList<User>();
			aliasizer = new Aliasizer();
			adminPin = randomizePin();
			gui.showMessage("Admin access pin: " + adminPin);
			blackList = new ArrayList<String>();
			usersToBeWrecked = new HashSet<User>();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void init(){
		autoWrecking.start();
		waitForConnectionThread.start();		
	}

	public void broadcast(Command c) {
		for (User u : userList) {
			u.send(c);
		}
		wreckNonRespondingUsers();
	}
	
	public synchronized void addUserToBeWrecked(User u) {
		usersToBeWrecked.add(u);
	}
	
	/**
	 * Safely wrecks all users in userList that are set to be wrecked, i.e. all
	 * users in usersToBeWrecked. 
	 * */
	private synchronized void wreckNonRespondingUsers() {
//		System.out.println("wrecking nonresponding users");
		Iterator<User> iter = userList.iterator();
		Map<String, String> usernameAndIp = new TreeMap<String, String>();
		boolean somebodyGotWrecked = false;
		while (iter.hasNext()) {
			User u = iter.next();
			if (usersToBeWrecked.contains(u)) {
				somebodyGotWrecked = true;
				String username = u.getName();
				String ip = u.getInetAddress().getHostAddress();
				usernameAndIp.put(username, ip);
				u.closeCrap();
				iter.remove();
			}
		}
		usersToBeWrecked.clear();
		if (somebodyGotWrecked) {
			broadcast(new Sound(StaticVariables.SERVER_LEAVECHAT));
			for (String username : usernameAndIp.keySet()) {
				broadcast(new Message(username + " decided to be uncool. What a bitch."));
				gui.showMessage("Connection problems: " + username + ": " + usernameAndIp.get(username));
			}
			wreckNonRespondingUsers();
			updateUsersWindow();
		}
	}

	public List<String> getUsernamesList() {
		List<String> usernames = new ArrayList<String>();
		for (User u : userList) {
			usernames.add(u.getName());
		}
		return usernames;
	}

	public Aliasizer getAliasizer() {
		return aliasizer;
	}

	public void kick(User kicker, String username) {
		for (User u : userList) {
			if (u.getName().equals(username)) {
				broadcast(new Sound(StaticVariables.SERVER_MOVEBITCH)); //TODO creating a command outside of factory
				broadcast(new Message(u.getName() + ", fuck off bitch.")); //TODO creating a command outside of factory
				u.send(new Disconnect());
				u.closeCrap();
				userList.remove(u);
				updateUsersWindow();
				broadcastUsernameList();
				return;
			}
		}
		kicker.send(new Message("That bitch isn't in this chat, yo."));
		wreckNonRespondingUsers();
	}

	private void broadcastUsernameList() {
		for(User u : userList) {
			(new BitchList()).serverRun(u); //TODO creating a command outside of factory
		}
	}

	private void updateUsersWindow() {
		List<String> usernames = new ArrayList<String>();
		for (User u : userList) {
			usernames.add(u.getName());
		}
		gui.updateUsersWindow(usernames);
		broadcast(new UpdateUsersWindow(usernames));
	}

	public String getIp(String username) {
		for (User u : userList) {
			if (u.getName().equals(username)) {
				return u.getInetAddress().getHostAddress();
			}
		}
		return null;
	}

	public ServerGui getServerGui() {
		return gui;
	}

	public void ban(User unbanner, String username) {
		for (User u : userList) {
			if (u.getName().equals(username)) {
				addUserToBeWrecked(u);
				blackList.add(u.getInetAddress().getHostAddress());
			}
		}
		wreckNonRespondingUsers();
	}

	public void unban(User unbanner, String ip) {
		if (blackList.contains(ip)) {
			blackList.remove(ip);
			unbanner.send(new Message(ip
					+ " was removed from the ban list. Hope the bitch keeps his manners this time."));
		} else {
			unbanner.send(new Message(ip + " isn't banned, bitch."));
		}
		wreckNonRespondingUsers();
	}

	public List<String> getBannedList() {
		wreckNonRespondingUsers();
		return blackList;
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

	private Thread waitForConnectionThread = new Thread() {
		@Override
		public void run() {
			while (!Thread.currentThread().isInterrupted()) {
				Socket s;
				try {
					s = gatekeeper.accept();
					if (!blackList.contains(s.getInetAddress().toString())) {
						User u = new User(s, me);
						gui.showMessage(u.getInetAddress().getHostAddress() + " is joining...");
						userList.add(u);
						broadcast(new Message(u.getName() + " has joined."));
						broadcast(new Sound(StaticVariables.SERVER_JOINCHAT));
						gui.showMessage(u.getName() + " has ip " + u.getInetAddress().getHostAddress());
						updateUsersWindow();
						broadcastUsernameList();
					} else {
						gui.showMessage(s.getInetAddress().toString()
								+ " is banned and he tried to join. Lol, who dis bitch think he is?");
						s.close();
					}
				} catch (IOException e) {
					gui.showMessage("Some bitch really sucks at connecting.");
					e.printStackTrace();
				}
			}
		}
	};
	
	private Thread autoWrecking = new Thread(){
		@Override
		public void run() {
			long waitTime = 1000;
			while (!isInterrupted()) {
				try {
					for (User u : userList) {
						u.ping();
					}
					wreckNonRespondingUsers();
					sleep(waitTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};

}
