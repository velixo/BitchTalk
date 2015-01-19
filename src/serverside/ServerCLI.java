package serverside;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ServerCLI implements ServerGui {

	@Override
	public void showMessage(String m) {
		System.out.println(getDateTime() + ": " + m);
	}

	@Override
	public void updateUsersWindow(List<String> users) {
		System.out.println(getDateTime() + ": Users currently in chatroom");
		for (String user : users) {
			System.out.println(user);
		}
	}

	private String getDateTime() {
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date d = new Date();
		return df.format(d);
	}
}
