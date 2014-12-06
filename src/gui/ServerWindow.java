package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import serverside.ServerGui;

public class ServerWindow extends JFrame implements ServerGui {
	private JTextArea chatWindow;
	private String windowTitle = "BitchTalk Server: ";

	public ServerWindow() {
		super("BitchTalk Server: ");
		chatWindow = new JTextArea();
		add(new JScrollPane(chatWindow), BorderLayout.CENTER);
	}
	
	@Override
	public void showMessage(String m) {
		chatWindow.append(m);
	}

}
