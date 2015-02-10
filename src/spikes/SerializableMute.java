package spikes;

import clientSide.ClientGui;
import command.Command;

public class SerializableMute implements SerializableCommand {
	private static final long serialVersionUID = -3576301348019701487L;
	private ClientGui c;
	
	public SerializableMute(ClientGui c) {
		this.c = c;
	}

	@Override
	public void run() {
		c.setMuteNotificationSound(true);
		c.showMessage("Notification sound muted, bitch.");
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof SerializableMute) {
			SerializableMute mute = (SerializableMute) o;
			return c.equals(mute.c);
		}
		return false;
	}
}
