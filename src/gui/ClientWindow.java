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

import statics.StaticVariables;
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
	private Clip openSound;
	private Clip celebrateSound;

	private boolean notificationMuted = false;
	
	private final static String USERJOINED = "other_joinchatsound.wav";
	private final static String USERLEFT = "other_leavechatsound.wav";
	private final static String NOTIFICATION ="other_notificationsound.wav";
	
	private boolean notificationSoundLoaded = false;
	private boolean userLeftLoaded = false;
	private boolean userJoinedLoaded = false;
	private boolean wooloolooLoaded = false;
	private boolean bossAssBitchLoaded = false;
	private boolean whatsGoingOnLoaded = false;
	private boolean moveBitchLoaded = false;
	private boolean openLoaded = false;
	private boolean celebrateLoaded = false;
	
	public final String WOOLOOLOO = StaticVariables.WOOLOOLOO;
	public final String BOSSASSBITCH = StaticVariables.BOSSASSBITCH;
	public final String WHATSGOINGON = StaticVariables.WHATSGOINGON;
	public final String MOVEBITCH = StaticVariables.SERVERMOVEBITCHGETOUTDAWAY;
	public final String OPEN = StaticVariables.OPEN;
	public final String CELEBRATE = StaticVariables.CELEBRATE;
	
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
		
//		loadSounds();
		
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
			playSound(NOTIFICATION);
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
			playSound(USERLEFT);
		}
		if (checkUserJoined(usernames, usersInConvo)) {
			System.out.println("UserJoined");
			playSound(USERJOINED);
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

	public void playSound(String soundFileName) {
		try {
			Clip sound = AudioSystem.getClip();
			File file = new File("res/" + soundFileName);
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
			sound.open(inputStream);
			sound.setMicrosecondPosition(0);
			sound.start();
		} catch (IOException | LineUnavailableException | UnsupportedAudioFileException | IllegalArgumentException e) {
			showSilentMessage("Bitch, you aint even got " + soundFileName);
		} 
		System.out.println("Play " + soundFileName);
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
