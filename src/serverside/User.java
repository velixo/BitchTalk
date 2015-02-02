package serverside;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

import command.Command;
import command.serverside.ServerCommandFactory;

public class User {
	private String name;
	private Socket connection;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	protected static int userCount = 0;
	private Server server;
	private User me = this;
	ServerCommandFactory commander;
	private boolean isAdmin = false;
	
	public User(Socket c, Server s) throws IOException {
		connection = c;
		input = new ObjectInputStream(connection.getInputStream());
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		name = "Bitch nr" + userCount++;
		server = s;
		commander = new ServerCommandFactory(this, server);	//lol
		checkmail.start();
	}
	
	//TODO maybe needs to be throws exception again...? we need to know when send is used in a for-loop that removes users from Server.userList
	public void send(String message){
		try {
			output.writeObject(message);
			output.flush();
		} catch (IOException e) {
			server.addUserToBeWrecked(me);
		}
		
	}
	
	public void sendUserList(List<String> list) {
		try {
			output.writeObject(list);
			output.flush();
		} catch (IOException e) {
			server.addUserToBeWrecked(me);
		}
	}
	
	public void setName(String newName) {
		name = newName;
		server.broadcastUsernameList();
	}
	
	public String getName() {
		return name;
	}
	
	public InetAddress getInetAddress() {
		return connection.getInetAddress();
	}
	
	public void setAdmin(boolean b) {
		isAdmin = b;
	}

	public boolean isAdmin() {
		return isAdmin;
	}
	
	public void closeCrap() {
		try{
			output.close();
			input.close();
			connection.close();
			
			//TODO ask isak if we should do this
//			output = null;
//			input = null;
//			connection = null;
         } catch(IOException ioe) {
        	ioe.printStackTrace();
         }
	}
	
	Thread checkmail = new Thread(){
		public void run(){
			boolean wrecked = false;
			while(!wrecked){
				try {
					String m = (String) input.readObject();
					if(m.charAt(0)=='/'){
						Command c = commander.build(m);
						c.run();
					}
					else{
						server.broadcastWithAlias(getName() + ": " + m);						
					}
				} catch (ClassNotFoundException | IOException e) {
					wrecked = true;
					server.addUserToBeWrecked(me);
				}				
			}
			
		}
	};
}
