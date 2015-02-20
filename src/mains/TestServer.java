package mains;

import serverside.Server;
import gui.ServerWindow;

public class TestServer {
	
	public static void main(String[] args) {
		ServerWindow window = new ServerWindow();
		Server s = new Server(window);
		s.init();
	}
}
