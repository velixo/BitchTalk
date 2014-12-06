package clientSide;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.StringTokenizer;

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
			StringTokenizer tkn = new StringTokenizer(message);
			if(tkn.nextToken().equals("/connect") && tkn.countTokens()>=1){
				connect(tkn.nextToken());
			}
			else if(output!=null){
				output.writeObject(message);				
			}
			else{
				gui.showMessage("You are not connected to any server");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
