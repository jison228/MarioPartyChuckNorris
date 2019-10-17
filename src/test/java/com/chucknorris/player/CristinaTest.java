package com.chucknorris.player;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CristinaTest {
	Cristina cristi;
	Player p1;
	Player p2;
	Player p3;
	List<Player> players;
	
	@Before
	public void executedBeforeEach() {
		cristi = new Cristina(30, 100);
		p1 = new Player("Dummy", 50, 100);
		p2 = new Player("Dummy", 20, 100);
		p3 = new Player("Dummy", 10, 100);
		players = new ArrayList<Player>();
		players.add(cristi);
		players.add(p1);
		players.add(p2);
		players.add(p3);
	}
	
	@Test
	public void getCharacterTest() {
		assertEquals("Cristina",cristi.getCharacter());
	}
	
	@Test
	public void applyPowerupTest() {
		cristi.applyPowerup(players, null);
		assertEquals(50, (int)p1.getPesos());
		assertEquals(35, (int)p2.getPesos());
		assertEquals(25, (int)p3.getPesos());
		assertEquals(45, (int)cristi.getPesos());
	}
}
