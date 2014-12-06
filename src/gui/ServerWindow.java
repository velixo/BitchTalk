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
		chatWindow.setEditable(false);
		add(new JScrollPane(chatWindow), BorderLayout.CENTER);
		
		setSize(300,550);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	@Override
	public void showMessage(String m) {
		chatWindow.append(m);
	}

}
