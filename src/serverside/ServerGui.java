package serverside;

import java.util.List;

public interface ServerGui {
	/**show the message m to the gui user.*/
	public void showMessage(String m);
	public void updateUsersWindow(List<String> users);
	
}
