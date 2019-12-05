package com.chucknorris.player;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DelCanioTest {
	DelCanio nico;
	Player p1;
	Player p2;
	Player p3;
	List<Player> players;

	@Before
	public void executedBeforeEach() {
		nico = new DelCanio(15, 100, 100);
		p1 = new PlayerBuilder()
				.setDolar(100)
				.setPesos(40)
				.build();
		p2 = new PlayerBuilder()
				.setDolar(100)
				.setPesos(20)
				.build();
		p3 = new PlayerBuilder()
				.setDolar(100)
				.setPesos(25)
				.build();
		players = new ArrayList<Player>();
		players.add(nico);
		players.add(p1);
		players.add(p2);
		players.add(p3);
		nico.applyPowerup(players, null);
	}

	@Test
	public void getCharacterTest() {
		assertEquals("Del Caño", nico.getCharacter());
	}

	@Test
	public void applyPowerupTest() {
		int test = 25;
		assertEquals(test, (int) p1.getPesos());
		assertEquals(test, (int) p2.getPesos());
		assertEquals(test, (int) p3.getPesos());
		assertEquals(test, (int) nico.getPesos());
	}
}
