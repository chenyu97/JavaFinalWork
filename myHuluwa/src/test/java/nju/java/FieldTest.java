package nju.java;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FieldTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Field field = new Field();
		
		assertEquals(field.getIsPlayback(), false);
		assertEquals(field.getCompleted(), false);
		
		//fail("Not yet implemented");
	}

}
