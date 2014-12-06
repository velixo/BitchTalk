package serverside;

import java.io.*;
import java.net.*;

public class User {
	private String name;
	private Socket connection;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	protected static int userCount = 0;
	private Server server;
	private User me = this;
	
	public User(Socket c, Server s) throws IOException{
		connection = c;
		input = new ObjectInputStream(connection.getInputStream());
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		name = "Bitch nr" + userCount++;
		server = s;
		checkmail.start();
	}
	public void send(String message) throws IOException{
		output.writeObject(message);
		output.flush();
	}
	public String getName(){
		return name;
	}
	public void closeCrap(){
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
			while(true){
				try {
					String m = (String) input.readObject();
					server.broadcast(m);
				} catch (ClassNotFoundException | IOException e) {
					server.wreck(me);
				}				
			}
			
		}
	};
}
