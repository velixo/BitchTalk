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
	public String readMessage() throws ClassNotFoundException, IOException{
		return (String) input.readObject();
	}
	public boolean hasMessage() throws IOException{
		return input.available()>0;
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
}
