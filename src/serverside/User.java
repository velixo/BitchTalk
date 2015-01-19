package serverside;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

import command.Command;
import command.ServerCommandFactory;

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
	
	public void send(String message) throws IOException {
		output.writeObject(message);
		output.flush();
	}
	
	public void send(List<String> list) throws IOException {
		output.writeObject(list);
		output.flush();
	}
	
	public String name() {
		return name;
	}
	
	public InetAddress getInetAddress() {
		return connection.getInetAddress();
	}

	public boolean isAdmin() {
		return isAdmin;
	}
	
	public void setAdmin(boolean b) {
		isAdmin = b;
	}
	
	public void setName(String newName) {
		name = newName;
		server.broadcastUsernameList();
	}
	
	public void closeCrap() {
		try{
			output.close();
			input.close();
			connection.close();
         }catch(IOException ioe){
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
						server.broadcast(name() + ": " + m);						
					}
				} catch (ClassNotFoundException | IOException e) {
					wrecked = true;
					server.wreck(me);
				}				
			}
			
		}
	};
}
