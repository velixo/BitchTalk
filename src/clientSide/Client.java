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
		
//		try {
//			if (message.charAt(0) == '/' && message.charAt(1) != ':' && factory.canBuild(message)) {
//				Command ca = factory.build(message);
//				ca.clientRun(this);
//			} else if(isSound(message)) {
//				sendAsSound(message);
//				
//			} else if(isAdminSound(message)) {
//				sendAsAdminSound(message);
//				
//			} else if(output!=null){
//				output.writeObject(message);
//				output.flush();
//				System.out.println("flushed, bitch");
//			}
//			else{
//				gui.showMessage("You are not connected to any server.");
//			}
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public ClientGui getGui() {
		return gui;
	}
	
//	private boolean isSound(String input) {
//		String soundName = input.replace("/", "") + ".wav";
//		return soundExists(soundName);
//	}
//	
//	private boolean isAdminSound(String input) {
//		String soundName = input.replace("/", "admin_") + ".wav";
//		return soundExists(soundName);
//	}
	
//	private boolean soundExists(String soundName) {
//		File soundFolder = new File("res/");
//		File[] sounds = soundFolder.listFiles();
//		for (File sound : sounds){
//			if (sound.getName().equals(soundName))
//				return true;
//		}
//		return false;
//	}
	
//	private void sendAsAdminSound(String message) throws IOException {
//		message = message.replace("/", "/:a:");
//		if(output!=null){
//			output.writeObject(message);
//			output.flush();
//			System.out.println("flushed, bitch");
//		}
//		else{
//			gui.showMessage("You are not connected to any server.");
//		}
//	}
	
//	private void sendAsSound(String message) throws IOException {
//		message = message.replace("/", "/:s:");
//		if(output!=null){
//			output.writeObject(message);
//			output.flush();
//			System.out.println("flushed, bitch");
//		}
//		else{
//			gui.showMessage("You are not connected to any server.");
//		}
//	}
	
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
			//TODO disconnect functionality
			while(runThread) {
				try {
					Command c = (Command) input.readObject();
					c.clientRunRecieved(me);
					
					
					
					
					//TODO refactor this code. assuming the object is a String or a List<String> is iffy design.
//					Object received = input.readObject();
//					if (received instanceof String) {
//						String message = (String) received;
//						System.out.println("Client.ListenForMessagesThread: " + message);
//						if (message.charAt(0) == '/') {
//							Command c = factory.build(message);
//							c.serverRun();
//						} else {
//							gui.showMessage(message);
//						}
//					} else if (received instanceof List){
//						@SuppressWarnings("unchecked")
//						List<String> usernames = (List<String>) received;	//since the only time we send over a list is when we update userList, we presume its a list of usernames.
//						gui.updateUsersWindow(usernames);
//					}
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
