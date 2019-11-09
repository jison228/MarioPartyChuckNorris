package com.chucknorris.game;

import com.chucknorris.commons.Position;
import com.chucknorris.gamemap.GameMap;
import com.chucknorris.gamemap.initiallizer.file.reader.csv.MapFileCSVReader;
import com.chucknorris.player.Player;
import com.chucknorris.player.PlayerBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GameMapTest {
	GameMap mapa1;
	Player p1, p2;
	ArrayList<Player> playerList;

	@Before
	public void executedBeforeEach() throws Exception {
		p1 = new PlayerBuilder()
				.setCharacter("Javier Milei")
				.build();
		p2 = new PlayerBuilder()
				.setCharacter("MauriCEOMcree")
				.build();
		List<Player> playerList = Arrays.asList(p1, p2);

		MapFileCSVReader mapFileCSVReader = new MapFileCSVReader("map_1.txt");
		mapa1 = mapFileCSVReader.buildGameMap();
		mapa1.initializePlayers(playerList);
	}

	@Test
	public void initializePlayers_test() {
		assertEquals(new Position(0, 0), p1.getNodeLocation().getPositionCoords());
		assertEquals(new Position(0, 0), p2.getNodeLocation().getPositionCoords());
	}

	@Test
	public void movePlayer_with_bif_test() {
		// TIRA JUGADOR 1
		int respuestaReal = mapa1.movePlayer(p1, 4);

		assertEquals(new Position(2, 5), p1.getNodeLocation().getPositionCoords());
		assertEquals(0, respuestaReal);
	}

	@Test
	public void movePlayer_without_bif_test() {
		// TIRA JUGADOR 2
		int respuestaReal2 = mapa1.movePlayer(p2, 8);

		assertEquals(new Position(3, 4), p2.getNodeLocation().getPositionCoords());
		assertEquals(2, respuestaReal2);

		// ELIGE JUGADOR 2
		int respuestaReal3 = mapa1.movePlayerFromIntersection(p2, p2.getNodeLocation().nextNodes().get(0), 2);

		assertEquals(new Position(2, 2), p2.getNodeLocation().getPositionCoords());
		assertEquals(0, respuestaReal3);

	}
}
