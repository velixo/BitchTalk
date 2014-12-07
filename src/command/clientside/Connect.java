package command.clientside;

import clientSide.Client;
import command.Command;

public class Connect implements Command{
	
	String address;
	Client client;
	
	public Connect(String ip, Client c){
		address = ip;
		client = c;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		client.connect(address);
	}

}
