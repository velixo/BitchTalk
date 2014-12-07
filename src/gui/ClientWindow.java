package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import sun.applet.Main;
import clientSide.Client;
import clientSide.ClientGui;

public class ClientWindow extends JFrame implements ClientGui {
	private Client client;
	
	private JTextArea chatWindow;
	private JTextField chatInput;
	private JTextArea usersInConvoWindow;
	
	private Clip clip;
	private final String notificationSoundName = "notificationSound_16bit.wav";
	private boolean notificationSoundLoaded = false;
	private boolean notificationSoundMuted = false;
	
	public ClientWindow() {
		super("Talking to dem bitchez: ");
		client = new Client(this);
		
		chatWindow = new JTextArea();
		chatWindow.setEditable(false);
		add(new JScrollPane(chatWindow), BorderLayout.CENTER);
		
		chatInput = new JTextField();
		chatInput.setEditable(true);
		chatInput.addActionListener(new ServerSendMessageListener());
		add(chatInput, BorderLayout.SOUTH);
		
		usersInConvoWindow = new JTextArea();
		usersInConvoWindow.setEditable(false);
		usersInConvoWindow.append("Users currently in this chat: \n");
		add(new JScrollPane(usersInConvoWindow), BorderLayout.EAST);
		
		loadNotificationSound();
		
		setSize(450,550);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void showMessage(String m) {
		chatWindow.append(m + "\n");
		if (!isActive() || !isFocused()) {	//not sure which one to use or what the difference is
			playNotificationSound();
		}
	}
	
	public void playNotificationSound() {
		if (notificationSoundLoaded && !notificationSoundMuted) {
			clip.setMicrosecondPosition(0);
			clip.start();
			System.out.println("clip start");
		}
	}
	
	public void loadNotificationSound() {
		try {
			clip = AudioSystem.getClip();
			File file = new File("res/" + notificationSoundName);
			System.out.println(file.toString());
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
			clip.open(inputStream);
			notificationSoundLoaded = true;
		} catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
			showMessage("Notification sound could not be loaded. Deal with it, bitch.");
		}
	}
	
	public void updateUsersWindow(List<String> users) {
		usersInConvoWindow.setText("Users currently in this chat:\n");
		for (String u : users) {
			usersInConvoWindow.append(u + "\n");
		}
	}
	
	public void setMuteNotificationSound(boolean b) {
		notificationSoundMuted = b;
	}
	
	public boolean getNotificationSoundMuted() {
		return notificationSoundMuted;
	}

	private class ServerSendMessageListener implements ActionListener {
		
		public ServerSendMessageListener() {
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String message = chatInput.getText();
			if (!message.equals("")) {
				client.send(message);
				chatInput.setText("");
			}
		}
	}
	
	
}
