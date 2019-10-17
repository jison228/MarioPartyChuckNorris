package com.chucknorris.player;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class DelCanioTest {
	DelCanio nico;
	Player p1;
	Player p2;
	Player p3;
	List<Player> players;

	@Before
	public void executedBeforeEach() {
		nico = new DelCanio(15, 100);
		p1 = new Player("Dummy", 40, 100);
		p2 = new Player("Dummy", 20, 100);
		p3 = new Player("Dummy", 25, 100);
		players = new ArrayList<Player>();
		players.add(nico);
		players.add(p1);
		players.add(p2);
		players.add(p3);
	}
	
	@Test
	public void getCharacterTest() {
		assertEquals("Del Caño",nico.getCharacter());
	}
	
	@Test
	public void applyPowerupTest() {
		nico.applyPowerup(players, null);
		int test = 25;
		assertEquals(test, (int)p1.getPesos());
		assertEquals(test, (int)p2.getPesos());
		assertEquals(test, (int)p3.getPesos());
		assertEquals(test, (int)nico.getPesos());
	}
}
