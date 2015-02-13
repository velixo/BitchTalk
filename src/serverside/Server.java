package serverside;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import shared.StaticVariables;
import command.Command;
import command.Disconnect;
import command.Message;
import command.Sound;
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
			waitForConnectionThread.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	public void broadcastWithAlias(String m) {
//		String aliasedMessage = aliasizer.aliasify(m);
//		broadcast(aliasedMessage);
//	}

	public void broadcast(Command c) {
		for (User u : userList) {
			u.send(c);
		}
		wreckNonRespondingUsers();
	}
	
//	public void broadcast(String message) {
//		gui.showMessage(message);
//		//TODO user.send might wreck himself. this happens inside a for-each statement, which is not safe. - fixed, i think
//		for (User u : userList) {
//			//TODO send is dangerous - fixed, i think
//			u.send(message);
//		}
//		wreckNonRespondingUsers();
//	}
//
//	public void broadcastUsernameList() {
//		List<String> usernames = getUsernamesList();
//		for (User u : userList) {
//			u.sendUserList(usernames);
//		}
//		wreckNonRespondingUsers();
//	}
	
	public synchronized void addUserToBeWrecked(User u) {
		usersToBeWrecked.add(u);
	}
	
	/**
	 * Safely wrecks all users in userList that are set to be wrecked, i.e. all
	 * users in usersToBeWrecked. 
	 * */
	private synchronized void wreckNonRespondingUsers() {
		Iterator<User> iter = userList.iterator();
		boolean somebodyGotWrecked = false;
		while (iter.hasNext()) {
			User u = iter.next();
			if (usersToBeWrecked.contains(u)) {
				somebodyGotWrecked = true;
				gui.showMessage("Connection problems: " + u.getName() + ": " + u.getInetAddress().getHostAddress());
				String username = u.getName();
				u.closeCrap();
				iter.remove();
				broadcast(new Message(username + " decided to be uncool. What a bitch."));
				updateUsersWindow();
			}
		}
		usersToBeWrecked.clear();
		if (somebodyGotWrecked)
			broadcast(new Sound(StaticVariables.SERVER_LEAVECHAT));
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

//	public void wreck(User u) {
//		u.closeCrap();
//		userList.remove(u);
//		broadcastWithAlias(u.getName() + " decided to be uncool. What a bitch.");
//		updateUsersWindow();
//		broadcastUsernameList();
//	}

	public void kick(User kicker, String username) {
		//TODO iterator
		for (User u : userList) {
			if (u.getName().equals(username)) {
				//TODO wreckNonRespondingUsers will be called here, which is quite unsafe considering we're in a for-loop.
				// create new broadcast that doesn't call wreckNonRespondingUsers()?
				broadcast(new Sound(StaticVariables.SERVER_MOVEBITCH)); //TODO creating a command outside of factory
				broadcast(new Message(u.getName() + ", fuck off bitch.")); //TODO creating a command outside of factory
				//TODO send is dangerous
				u.send(new Disconnect());
				u.closeCrap();
				userList.remove(u);
				updateUsersWindow();
				broadcastUsernameList();
				return;
			}
		}
		//TODO send is dangerous - fixed, i think
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
			//TODO send is dangerous - fixed, i think
			unbanner.send(new Message(ip
					+ " was removed from the ban list. Hope the bitch keeps his manners this time."));
		} else {
			//TODO send is dangerous - fixed, i think
			unbanner.send(new Message(ip + " isn't banned, bitch."));
		}
		wreckNonRespondingUsers();
	}

//	public void sendBannedList(User u) {
//		StringBuilder message = new StringBuilder(
//				"The following IP's are banned: \n");
//		for (String ip : blackList) {
//			message.append(ip + "\n");
//		}
//		//TODO send is dangerous - fixed, i think
//		u.send(message.toString());
//		wreckNonRespondingUsers();
//	}
	
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
		public void run() {
			while (!Thread.currentThread().isInterrupted()) {
				Socket s;
				try {
					s = gatekeeper.accept();
					if (!blackList.contains(s.getInetAddress().toString())) {
						User u = new User(s, me);
						gui.showMessage(u.getInetAddress().getHostAddress() + " is joining...");
//						for (User i : userList) {
//							if (i.getInetAddress().equals(u.getInetAddress())) {
//								userList.remove(i);
//							}
//						}
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

}
