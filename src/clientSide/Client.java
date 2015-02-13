package clientSide;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import command.Command;
import command.clientside.ClientCommandFactory;

public class Client {
	
	private Socket connection;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	
	private ClientGui gui;
	private ListenForMessagesThread listenForMessagesThread = new ListenForMessagesThread();
	private Client me = this;
	
	public Client(ClientGui g){
		gui = g;
		gui.showMessage(ClientCommandFactory.help());
	}
	
	//TODO check that this code is correct
	private boolean connected() {
		if (connection != null)
			return !connection.isClosed();
		return false;
	}
	
	public void connect(String ip){
		if (connected()) {
			gui.showMessage("Bitch, you're already connected. Get a fucking grip.");
		} else {
			try {
				connection = new Socket(InetAddress.getByName(ip),9513);
				output = new ObjectOutputStream(connection.getOutputStream());
				output.flush();
				input = new ObjectInputStream(connection.getInputStream());
				if (listenForMessagesThread != null)
					listenForMessagesThread.stopThread();
				listenForMessagesThread = new ListenForMessagesThread();
				listenForMessagesThread.start();
			} catch (IOException e) {
				gui.showMessage("I'm afraid I can't let you do that, bitch.");
			}
		}
	}
	
	public void buildAndRunCommand(String input) {
		Command c = ClientCommandFactory.build(input);
		c.clientRun(this);
	}
	
	public void send(Command c){
		try {
			if(output!=null){
				output.writeObject(c);
				output.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ClientGui getGui() {
		return gui;
	}
	
	public void closeCrap(){
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
			while(runThread) {
				try {
					Command c = (Command) input.readObject();
					c.clientRunRecieved(me);
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
