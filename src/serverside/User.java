package serverside;

import java.io.*;
import java.net.*;

public class User {
	private String name;
	private Socket connection;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	protected static int userCount = 0;
	
	public User(Socket c) throws IOException{
		connection = c;
		input = new ObjectInputStream(connection.getInputStream());
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		name = "Bitch nr" + userCount++;
	}
	
	public void send(String message) throws IOException{
		output.writeObject(message);
		output.flush();
	}
}
