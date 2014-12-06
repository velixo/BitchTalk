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
			gui.showMessage("Bitch, I'm trying to connect. Get off my fucking back, OK???");
			connection = new Socket(InetAddress.getByName(ip),9513);
			System.out.println("BITCH1");
			output = new ObjectOutputStream(connection.getOutputStream());
			output.flush();
			input = new ObjectInputStream(connection.getInputStream());
			System.out.println("AAAAYYYY BITCH");
			listenForMessagesThread.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void send(String message){
		try {
			StringTokenizer tkn = new StringTokenizer(message);
			if(tkn.nextToken().equals("/connect") && tkn.countTokens()>=1){	// >=2?
				System.out.println("I am in client.send()!");
				//TODO: Kanske stänga eventuell nuvarande streams/sockets?
				
				connect(tkn.nextToken());
			}
			else if(output!=null){
				output.writeObject(message);
				output.flush();
				System.out.println("flushed, bitch");
			}
			else{
				gui.showMessage("You are not connected to any server.");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void closeCrap(){
		gui.showMessage("bitch, I'm out.");
		try{
			output.close();
			input.close();
			connection.close();
         }catch(IOException ioe){
        	ioe.printStackTrace();
         }
	}
	
	private Thread listenForMessagesThread = new Thread() {
		public void run() {
			//TODO disconnect functionality
			while(true) {
				try {
					String message = (String) input.readObject();
					gui.showMessage(message);
				} catch (ClassNotFoundException | IOException e) {
					gui.showMessage("Disconnected from server");
					closeCrap();
					break;
				}
			}
		}
	};
}
