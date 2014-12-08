package test;

import serverside.Server;
import gui.ServerWindow;

public class TestServer {
	
	public static void main(String[] args) {
		ServerWindow window = new ServerWindow();
		new Server(window);
	}
}
