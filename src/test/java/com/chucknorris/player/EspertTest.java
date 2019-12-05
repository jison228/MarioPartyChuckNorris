package com.chucknorris.player;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EspertTest {
	Espert alesi;
	Player p1;
	Player p2;
	Player p3;
	List<Player> players;

	@Before
	public void executedBeforeEach() {
		alesi = new Espert(100, 50, 100);
		p1 = new PlayerBuilder()
				.setPesos(200)
				.setDolar(200)
				.build();
		p2 = new PlayerBuilder()
				.setPesos(400)
				.setDolar(200)
				.build();
		p3 = new PlayerBuilder()
				.setPesos(50)
				.setDolar(60)
				.build();
		players = new ArrayList<Player>();
		players.add(alesi);
		players.add(p1);
		players.add(p2);
		players.add(p3);
		alesi.applyPowerup(players, null);
	}

	@Test
	public void getCharacterTest() {
		assertEquals("Espert", alesi.getCharacter());
	}

	@Test
	public void applyPowerupTest() {
		assertEquals(50, (int) alesi.getDolares());
		assertEquals(50, (int) alesi.getPesos());
		assertEquals(100, (int) p1.getDolares());
		assertEquals(200, (int) p1.getPesos());
		assertEquals(200, (int) p2.getDolares());
		assertEquals(200, (int) p2.getPesos());
		assertEquals(25, (int) p3.getDolares());
		assertEquals(60, (int) p3.getPesos());
	}
}
