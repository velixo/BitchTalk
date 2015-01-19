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
	private Clip userJoinedSound;
	private Clip userLeftSound;
	private Clip wooloolooSound;
	private Clip bossAssBitchSound;
	private Clip whatsGoingOnSound;
	private Clip moveBitchSound;

	private boolean notificationMuted = false;
	
	private boolean notificationSoundLoaded = false;
	private boolean userLeftLoaded = false;
	private boolean userJoinedLoaded = false;
	private boolean wooloolooLoaded = false;
	private boolean bossAssBitchLoaded = false;
	private boolean whatsGoingOnLoaded = false;
	private boolean moveBitchLoaded = false;
	
	public final String WOOLOOLOO = "woolooloo";
	public final String BOSSASSBITCH = "bossassbitch";
	public final String WHATSGOINGON = "whatsgoingon";
	public final String MOVEBITCH = "movebitchgetoutdaway";
	
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
		
		loadBasicSounds();
		loadFunnySounds();
		
		setSize(450,550);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		client = new Client(this);
		chatInput.requestFocus();
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
		notificationMuted = b;
	}
	
	public boolean getNotificationSoundMuted() {
		return notificationMuted;
	}

	private void loadBasicSounds() {
		try {
			notificationSound = AudioSystem.getClip();
			File file = new File("src/res/notificationSound.wav");
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
			notificationSound.open(inputStream);
			notificationSoundLoaded = true;
		} catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
			showMessage("notificationSound.wav could not be loaded. Deal with it, bitch.");
			notificationSoundLoaded = false;
		}
		
		try {
			userJoinedSound = AudioSystem.getClip();
			File file = new File("src/res/joinChatSound.wav");
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
			userJoinedSound.open(inputStream);
			userJoinedLoaded = true;
		} catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
			showMessage("joinChatSound.wav could not be loaded. Deal with it, bitch.");
			userJoinedLoaded = false;
		}
		
		try {
			userLeftSound = AudioSystem.getClip();
			File file = new File("src/res/leaveChatSound.wav");
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
			userLeftSound.open(inputStream);
			userLeftLoaded = true;
		} catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
			showMessage("leaveChatSound.wav could not be loaded. Deal with it, bitch.");
			userLeftLoaded = false;
		}
		
		try {
			moveBitchSound = AudioSystem.getClip();
			File file = new File("src/res/moveBitchGetOutDaWay.wav");
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
			moveBitchSound.open(inputStream);
			moveBitchLoaded = true;
		} catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
			showMessage("moveBitchGetOutDaWay.wav could not be loaded. Deal with it, bitch.");
			moveBitchLoaded = false;
		}
	}

	private void playNotificationSound() {
		if (notificationSoundLoaded && !notificationMuted) {
			notificationSound.setMicrosecondPosition(0);
			notificationSound.start();
		}
	}
	
	private void playUserJoinedSound() {
		if (userJoinedLoaded) {
			userJoinedSound.setMicrosecondPosition(0);
			userJoinedSound.start();
		}
	}
	
	private void playUserLeftSound() {
		if (userLeftLoaded) {
			userLeftSound.setMicrosecondPosition(0);
			userLeftSound.start();
		}
	}
	
	public void playSound(String soundName) {
		switch (soundName) {
		case WOOLOOLOO:
			if (wooloolooLoaded) {
				wooloolooSound.setMicrosecondPosition(0);
				wooloolooSound.start();
			}
			break;

		case BOSSASSBITCH:
			if (bossAssBitchLoaded) {
				bossAssBitchSound.setMicrosecondPosition(0);
				bossAssBitchSound.start();
			}
			
		case WHATSGOINGON:
			if (whatsGoingOnLoaded) {
				whatsGoingOnSound.setMicrosecondPosition(0);
				whatsGoingOnSound.start();
			}
			
		case MOVEBITCH:
			if (moveBitchLoaded) {
				moveBitchSound.setMicrosecondPosition(0);
				moveBitchSound.start();
			}
			
		default:
			break;
		}
		System.out.println("Play " + soundName);
	}
	
	private void loadFunnySounds() {
		try {
			wooloolooSound = AudioSystem.getClip();
			File file = new File("src/res/woolooloo.wav");
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
			wooloolooSound.open(inputStream);
			wooloolooLoaded = true;
		} catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
			showMessage("woolooloo.wav could not be loaded. Deal with it, bitch.");
			wooloolooLoaded = false;
		}
		
		try {
			bossAssBitchSound = AudioSystem.getClip();
			File file = new File("src/res/bossAssBitch.wav");
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
			bossAssBitchSound.open(inputStream);
			bossAssBitchLoaded = true;
		} catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
			showMessage("bossAssBitch.wav could not be loaded. Deal with it, bitch.");
			bossAssBitchLoaded = false;
		}
		
		try {
			whatsGoingOnSound = AudioSystem.getClip();
			File file = new File("src/res/whatsGoingOn.wav");
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
			whatsGoingOnSound.open(inputStream);
			whatsGoingOnLoaded = true;
		} catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
			whatsGoingOnLoaded = false;
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
