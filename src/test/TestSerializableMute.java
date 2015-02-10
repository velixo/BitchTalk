package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import shared.Message;
import spikes.MockClientGui;
import spikes.SerializableMute;
import clientSide.ClientGui;

@SuppressWarnings("rawtypes")
public class TestSerializableMute {
	private String testFileDir = "test_files";
	private String testFilePath = testFileDir + "/testSerializableMute.txt";
	private ClientGui gui;

	@Before
	public void setup() {
		gui = new MockClientGui();
		new File(testFileDir).mkdir();
	}
	
	@Test
	public void testMuteWorks() {
		assertFalse(gui.getNotificationSoundMuted());
		
		SerializableMute muteCommand= new SerializableMute(gui);
		muteCommand.run();
		
		assertTrue(gui.getNotificationSoundMuted());
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testIsSerializable() {
		assertFalse(gui.getNotificationSoundMuted());
		//this represents creating a client command serverside. which is kinda very stupid
		SerializableMute muteCommand= new SerializableMute(gui);
		
		Message<SerializableMute> expectedMessage = new Message<SerializableMute>("velixo", Message.COMMAND, muteCommand);
		writeToTestFile(expectedMessage);
		Message<SerializableMute> actualMessage  = readMessageFromTestFile();
		assertEquals(expectedMessage, actualMessage);
		
		muteCommand = actualMessage.getContent();
		muteCommand.run();
		//here the test fails because muteCommand is no longer connected to the gui. muteCommand.run() therefore does not affect gui
		assertTrue(gui.getNotificationSoundMuted());
	}
	
	@After
	public void deleteTestFiles() {
		try {
			Files.deleteIfExists(Paths.get(testFilePath));
			Files.deleteIfExists(Paths.get(testFileDir));
		} catch (IOException e) {
			System.out.println("Error deleting file");
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	private Message readMessageFromTestFile() {
		Message actualMessage = null;
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream(testFilePath));
			actualMessage = (Message) input.readObject();
			input.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return actualMessage;
	}
	
	private void writeToTestFile(Message expectedMessage) {
		try {
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(testFilePath));
			output.writeObject(expectedMessage);
			output.flush();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
