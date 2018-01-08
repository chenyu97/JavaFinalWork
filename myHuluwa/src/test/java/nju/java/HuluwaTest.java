package nju.java;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HuluwaTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Field field = new Field();
		
		Huluwa huluwa = new Huluwa(30, 30, field, "7wa.png", COLOR.values()[6], SENIORITY.values()[6], 0);
		
		assertEquals(huluwa.getIsLive(), true);
		
		assertEquals(huluwa.getIsGood(), true);
		
	}

}
