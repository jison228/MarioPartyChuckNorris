package com.chucknorris.game;

import com.chucknorris.commons.Position;
import com.chucknorris.gamemap.GameMap;
import com.chucknorris.gamemap.initiallizer.file.reader.csv.MapFileCSVReader;
import com.chucknorris.player.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class MapTest {
	GameMap mapa1;
	Player p1, p2;
	ArrayList<Player> playerList;

	@Before
	public void executedBeforeEach() throws Exception {
		MapFileCSVReader mapFileCSVReader = new MapFileCSVReader("map_1.txt");
		mapa1 = mapFileCSVReader.buildGameMap();
		p1 = new Player("Javier Milei", 0);
		p2 = new Player("MauriCEOMcree", 0);
		playerList = new ArrayList<Player>();
		playerList.add(p1);
		playerList.add(p2);
		mapa1.initializePlayers(playerList);
	}

	@Test
	public void initializePlayers_test() {
		assertEquals(new Position(0, 0), p1.getPos().getPos());
		assertEquals(new Position(0, 0), p2.getPos().getPos());
	}

	@Test
	public void movePlayer_with_bif_test() {
		// TIRA JUGADOR 1
		int respuestaReal = mapa1.movePlayer(p1, 4);

		assertEquals(new Position(2, 5), p1.getPos().getPos());
		assertEquals(0, respuestaReal);
	}

	@Test
	public void movePlayer_without_bif_test() {
		// TIRA JUGADOR 2
		int respuestaReal2 = mapa1.movePlayer(p2, 8);

		assertEquals(new Position(3, 4), p2.getPos().getPos());
		assertEquals(2, respuestaReal2);

		// ELIGE JUGADOR 2
		int respuestaReal3 = mapa1.movePlayer(p2, 2, p2.getPos().nextNodes().get(0));

		assertEquals(new Position(2, 2), p2.getPos().getPos());
		assertEquals(0, respuestaReal3);

	}

	public void circularMap() {
		mapa1.movePlayer(p2, 8);
		mapa1.movePlayer(p2, 2, p2.getPos().nextNodes().get(0));
		// TIRA JUGADOR 2
		int respuestaReal4 = mapa1.movePlayer(p2, 5);

		assertEquals(0, respuestaReal4);
		assertEquals(new Position(0, 0), p2.getPos().getPos());
	}
}
