package command.clientside;

import serverside.User;
import clientSide.Client;

import command.Command;

public class Connect implements Command{
	private static final long serialVersionUID = -3359363378018918205L;
	private String address;
	
	public Connect(String ip){
		address = ip;
	}
	@Override
	public void serverRun(User u) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void clientRun(Client c) {
		c.connect(address);
	}
	@Override
	public void clientRunRecieved(Client c) {
		// TODO Auto-generated method stub
		
	}

}
