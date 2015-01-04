package test;

import serverside.Server;
import serverside.ServerCLI;
import serverside.ServerGui;

public class TestServerCLI {

	public static void main(String[] args) {
		ServerGui s = new ServerCLI();
		new Server(s);
	}
}