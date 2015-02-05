package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import serverside.Aliasizer;

public class TestAlias {
	Aliasizer ali;
	
	@Before
	public void setUp() throws Exception {
		ali = new Aliasizer();
	}

	@Test
	public void test() {
		ali.set("dig","deg");
		ali.set("saknar", "äter");
		ali.set("deg", "kneg");
		assertEquals("Jag äter deg.", ali.aliasify("Jag saknar dig."));
	}

}
