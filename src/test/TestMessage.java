package test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import shared.Message;
import shared.StaticVariables;

@SuppressWarnings("rawtypes")
public class TestMessage {
	private String testFileDir = "test_files";
	private String testFilePath = testFileDir + "/testMessage.txt";
	private String sender = "velixo";

	@Before
	public void setUpTestFilesFolder() {
		new File(testFileDir).mkdir();
	}
	
	@Test
	public void testTextMessage() {
		String text = "hej bajs";
		Message<String> expectedMessage = new Message<String>(sender, Message.TEXT, text);
		writeToTestFile(expectedMessage);
		Message actualMessage = readMessageFromTestFile();
		
		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	public void testCommand() {
		String command = StaticVariables.HELP;
		Message<String> expectedMessage = new Message<String>(sender, Message.COMMAND, command);
		writeToTestFile(expectedMessage);
		Message actualMessage = readMessageFromTestFile();
		
		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	public void testUserList() {
		ArrayList<String> userList = new ArrayList<String>();
		userList.add("voidcase");
		userList.add("bottomBitch");
		userList.add("Bitch King");
		
		Message<ArrayList<String>> expectedMessage = new Message<ArrayList<String>>(
				sender, Message.COMMAND, userList);
		writeToTestFile(expectedMessage);
		Message actualMessage = readMessageFromTestFile();
		
		assertEquals(expectedMessage, actualMessage);
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
