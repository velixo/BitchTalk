package spikes;

import java.util.List;
import java.io.Serializable;
import clientSide.ClientGui;

public class MockClientGui implements ClientGui, Serializable {
	private static final long serialVersionUID = 3359182350238006507L;
	private boolean muted;
	
	public MockClientGui() {
		muted = false;
	}
	
	@Override
	public void setMuteNotificationSound(boolean b) {
		muted = b;
	}

	@Override
	public boolean getNotificationSoundMuted() {
		return muted;
	}

	
	@Override
	public void showMessage(String m) {}
	@Override
	public void showSilentMessage(String m) {}
	@Override
	public void updateUsersWindow(List<String> usersInConvo) {}
	@Override
	public void playSound(String soundName) {}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof MockClientGui) {
			return muted == ((MockClientGui) o).muted;
		}
		return false;
	}
}
