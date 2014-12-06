package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientWindow extends JFrame {
	private String selfUsername;
	
	private JTextArea chatWindow;
	private JTextField chatInput;
	
	public ClientWindow() {
		super("Talking to: ");
		chatWindow = new JTextArea();
		chatWindow.setEditable(false);
		add(new JScrollPane(chatWindow), BorderLayout.CENTER);
		
		chatInput = new JTextField();
		chatInput.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						//TODO:
					}
				});
		
		setSize(450,550);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
}
