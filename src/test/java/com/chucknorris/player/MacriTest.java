package com.chucknorris.player;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class MacriTest {
	Macri gato;
	Player p1;
	Player p2;
	Player p3;
	List<Player> players;

	@Before
	public void executedBeforeEach() {
		gato = new Macri(15, 100, 100);
		p1 = new Player("Dummy", 40, 200);
		p2 = new Player("Dummy", 20, 300);
		p3 = new Player("Dummy", 25, 100);
		players = new ArrayList<Player>();
		players.add(gato);
		players.add(p1);
		players.add(p2);
		players.add(p3);
		gato.applyPowerup(players, null);
	}

	@Test
	public void getCharacterTest() {
		assertEquals("Macri", gato.getCharacter());
	}

	@Test
	public void applyPowerupTest() {
		assertEquals(240, (int) p1.getDolares());
		assertEquals(360, (int) p2.getDolares());
		assertEquals(100, (int) p3.getDolares());
		assertEquals(100, (int) gato.getDolares());
	}
}
