package spikes;

import java.util.List;

import clientSide.ClientGui;

public class MockClientGui implements ClientGui {
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

}
