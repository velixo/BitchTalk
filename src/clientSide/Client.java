package clientSide;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.StringTokenizer;

import command.ClientCommandFactory;
import command.Command;

public class Client {
	
	private Socket connection;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	
	private ClientGui gui;
	private ListenForMessagesThread listenForMessagesThread = new ListenForMessagesThread();
	private ClientCommandFactory factory;
	
	public Client(ClientGui g){
		gui = g;
		factory = new ClientCommandFactory(gui,this);
		gui.showMessage(factory.help());
	}
	
	public void connect(String ip){
		try {
			gui.showMessage("Bitch, I'm trying to connect. Get off my fucking back, OK???");
			connection = new Socket(InetAddress.getByName(ip),9513);
			System.out.println("BITCH1");
			output = new ObjectOutputStream(connection.getOutputStream());
			output.flush();
			input = new ObjectInputStream(connection.getInputStream());
			if (listenForMessagesThread != null)
				listenForMessagesThread.stopThread();
			listenForMessagesThread = new ListenForMessagesThread();
			listenForMessagesThread.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void send(String message){
		try {
			if (message.charAt(0) == '/' && !message.contains(":")) {
				Command c = factory.build(message);
				c.run();
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
	
	
	private class ListenForMessagesThread extends Thread {
		private boolean runThread;
		public ListenForMessagesThread() {
			runThread = true;
		}
		
		public void run() {
			//TODO disconnect functionality
			while(runThread) {
				try {
					String message = (String) input.readObject();
					if (message.charAt(0) == '/' && !message.contains(":")) {
						
					}
					gui.showMessage(message);
				} catch (ClassNotFoundException | IOException e) {
					gui.showMessage("Disconnected from server");
					closeCrap();
					break;
				}
			}
		}
		
		public void stopThread() {
			runThread = false;
		}
	}
	
}
