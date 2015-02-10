package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import spikes.MockClientGui;
import spikes.SerializableMute;
import clientSide.ClientGui;

public class TestSerializableMute {

	@Test
	public void testMuteWorks() {
		ClientGui gui = new MockClientGui();
		assertFalse(gui.getNotificationSoundMuted());
		
		SerializableMute muteCommand= new SerializableMute(gui);
		muteCommand.run();
		
		assertTrue(gui.getNotificationSoundMuted());
	}

}
