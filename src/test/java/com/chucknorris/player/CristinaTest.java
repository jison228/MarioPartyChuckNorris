package com.chucknorris.player;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CristinaTest {
	Cristina cristi;
	Player p1;
	Player p2;
	Player p3;
	List<Player> players;

	@Before
	public void executedBeforeEach() {
		cristi = new Cristina(30, 100, 100);
		p1 = new PlayerBuilder()
				.setPesos(50)
				.setDolar(100)
				.build();
		p2 = new PlayerBuilder()
				.setPesos(20)
				.setDolar(100)
				.build();
		p3 = new PlayerBuilder()
				.setPesos(10)
				.setDolar(100)
				.build();
		players = new ArrayList<Player>();
		players.add(cristi);
		players.add(p1);
		players.add(p2);
		players.add(p3);
		cristi.applyPowerup(players, null);
	}

	@Test
	public void getCharacterTest() {
		assertEquals("Cristina", cristi.getCharacter());
	}

	@Test
	public void applyPowerupTest() {
		assertEquals(50, (int) p1.getPesos());
		assertEquals(35, (int) p2.getPesos());
		assertEquals(25, (int) p3.getPesos());
		assertEquals(45, (int) cristi.getPesos());
	}
}
