package gui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import serverside.ServerGui;

public class ServerWindow extends JFrame implements ServerGui {
	
	private static final long serialVersionUID = -8948993873925401134L;
	
	private JTextArea chatWindow;
	private JTextArea usersInConvoWindow;

	public ServerWindow() {
		super("BitchTalk Server: ");
		chatWindow = new JTextArea();
		chatWindow.setEditable(false);
		add(new JScrollPane(chatWindow), BorderLayout.CENTER);
		
		usersInConvoWindow = new JTextArea();
		usersInConvoWindow.setEditable(false);
		usersInConvoWindow.append("Users currently in this chat: \n");
		add(new JScrollPane(usersInConvoWindow), BorderLayout.EAST);
		
		setSize(450,550);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	@Override
	public void showMessage(String m) {
		chatWindow.append(m + "\n");
	}
	
	public void updateUsersWindow(List<String> users) {
		usersInConvoWindow.setText("Users currently in this chat:\n");
		for (String u : users) {
			usersInConvoWindow.append(u + "\n");
		}
	}

}
