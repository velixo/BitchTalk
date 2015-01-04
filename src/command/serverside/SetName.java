package command.serverside;

import command.Command;

import serverside.User;

public class SetName implements Command{
	
	private User usr;
	private String newName;
	
	public SetName(User u, String n){
		usr = u;
		newName = n;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		usr.setName(newName);
	}

}
