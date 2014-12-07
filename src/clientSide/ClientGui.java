package clientSide;

public interface ClientGui {
	public void showMessage(String m);
	public void showSilentMessage(String m);
	public void setMuteNotificationSound(boolean b);
	public boolean getNotificationSoundMuted();
	public void userJoined(String username);
	public void userLeft(String username);
}
