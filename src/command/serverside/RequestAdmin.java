package command.serverside;

import java.io.IOException;

import serverside.Server;
import serverside.User;
import command.Command;

public class RequestAdmin implements Command {
	private Server s;
	private User u;
	private String pin;
	
	public RequestAdmin(Server s, User u, String pin) {
		this.s = s;
		this.u = u;
		this.pin = pin;
	}

	@Override
	public void run() {
		try {
			if (s.pinIsCorrect(pin)) {
				u.setAdmin(true);
				u.send("Congrats, bitch. You're now an admin. Big fucking whoop.");
			} else {
					u.send("Incorrect pin, bitch. You sure you're ready to be an admin?");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}

}
