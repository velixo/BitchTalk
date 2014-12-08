package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

import clientSide.Client;
import clientSide.ClientGui;

public class ClientWindow extends JFrame implements ClientGui {

	private static final long serialVersionUID = -2841093591038641088L;

	private Client client;
	
	private JTextArea chatWindow;
	private JTextField chatInput;
	private JTextArea usersInConvoWindow;
	private List<String> usersInConvo;
	
	private Clip notificationSound;
	private boolean notificationSoundLoaded = false;
	private boolean notificationSoundMuted = false;
	
	private Clip userJoinedSound;
	private boolean userJoinedSoundLoaded = false;
	
	private Clip userLeftSound;
	private boolean userLeftSoundLoaded = false;
	
	public ClientWindow() {
		super("Talking to dem bitchez: ");
		
		chatWindow = new JTextArea();
		chatWindow.setEditable(false);
		add(new JScrollPane(chatWindow), BorderLayout.CENTER);
		
		chatInput = new JTextField();
		chatInput.setEditable(true);
		chatInput.addActionListener(new ServerSendMessageListener());
		add(chatInput, BorderLayout.SOUTH);
		
		usersInConvo = new ArrayList<String>();
		usersInConvoWindow = new JTextArea();
		usersInConvoWindow.setEditable(false);
		usersInConvoWindow.append("Users currently in this chat: \n");
		add(new JScrollPane(usersInConvoWindow), BorderLayout.EAST);
		
		loadSounds();
		
		setSize(450,550);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		client = new Client(this);
	}
	
	@Override
	public void showMessage(String m) {
		chatWindow.append(m + "\n");
		if (!isActive() || !isFocused()) {	//not sure which one to use or what the difference is
			playNotificationSound();
		}
	}
	
	public void showSilentMessage(String m) {
		chatWindow.append(m + "\n");
	}
	
	public void updateUsersWindow(List<String> usernames) {
		usersInConvoWindow.setText("Users currently in this chat:\n");
		for (String u : usernames) {
			usersInConvoWindow.append(u + "\n");
		}
		if (checkUserLeft(usernames, usersInConvo)) {
			System.out.println("UserLeft");
			playUserLeftSound();
		}
		if (checkUserJoined(usernames, usersInConvo)) {
			System.out.println("UserJoined");
			playUserJoinedSound();
		}
		usersInConvo = usernames;
	}
	
	private boolean checkUserLeft(List<String> usernames, List<String> usersInConvo) {
		for (String u : usersInConvo) {
			if (!usernames.contains(u)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean checkUserJoined(List<String> usernames, List<String> usersInConvo) {
		for (String u : usernames) {
			if (!usersInConvo.contains(u)) {
				return true;
			}
		}
		return false;
	}
	
	public void setMuteNotificationSound(boolean b) {
		notificationSoundMuted = b;
	}
	
	public boolean getNotificationSoundMuted() {
		return notificationSoundMuted;
	}

	private void loadSounds() {
		try {
			notificationSound = AudioSystem.getClip();
			File file = new File("res/notificationSound.wav");
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
			notificationSound.open(inputStream);
			notificationSoundLoaded = true;
		} catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
			showMessage("Notification sound could not be loaded. Deal with it, bitch.");
			notificationSoundLoaded = false;
		}
		
		try {
			userJoinedSound = AudioSystem.getClip();
			File file = new File("res/joinChatSound.wav");
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
			userJoinedSound.open(inputStream);
			userJoinedSoundLoaded = true;
		} catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
			showMessage("joinChatSound.wav could not be loaded. Deal with it, bitch.");
			userJoinedSoundLoaded = false;
		}
		
		try {
			userLeftSound = AudioSystem.getClip();
			File file = new File("res/leaveChatSound.wav"); 	//TODO update this file reference
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
			userLeftSound.open(inputStream);
			userLeftSoundLoaded = true;
		} catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
			showMessage("leaveChatSound.wav could not be loaded. Deal with it, bitch.");
			userJoinedSoundLoaded = false;
		}
	}

	private void playNotificationSound() {
		if (notificationSoundLoaded && !notificationSoundMuted) {
			notificationSound.setMicrosecondPosition(0);
			notificationSound.start();
		}
	}
	
	private void playUserJoinedSound() {
		if (userJoinedSoundLoaded) {
			userJoinedSound.setMicrosecondPosition(0);
			userJoinedSound.start();
		}
	}
	
	private void playUserLeftSound() {
		if (userLeftSoundLoaded) {
			userLeftSound.setMicrosecondPosition(0);
			userLeftSound.start();
		}
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
