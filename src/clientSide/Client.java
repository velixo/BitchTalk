package clientSide;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
	
	private Socket connection;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	
	private ClientGui gui;
	
	public Client(ClientGui g){
		gui = g;
	}
	
	public void connect(String ip){
		try {
			connection = new Socket(InetAddress.getByName(ip),9513);
			input = new ObjectInputStream(connection.getInputStream());
			output = new ObjectOutputStream(connection.getOutputStream());
			output.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void send(String message){
		try {
			output.writeObject(message);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
