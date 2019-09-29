package com.chucknorris.game;

import com.chucknorris.commons.Game;
import com.chucknorris.commons.Position;
import com.chucknorris.mapa.*;
import com.chucknorris.player.Player;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class JuegoTest {
	@Test
	public void test() throws FileNotFoundException {


		Map mapa1 = new Map("mapa1.txt");
		// MAPA CREADO

		Player p1 = new Player("Javier Milei", 0);
		Player p2 = new Player("MauriCEOMcree", 0);
		ArrayList<Player> playerList = new ArrayList<Player>();
		playerList.add(p1);
		playerList.add(p2);

		Game juego1 = new Game(playerList, mapa1);

		mapa1.initializePlayers(juego1.getPlayerList());
		// LISTO PARA JUGAR

		// TIRA JUGADOR 1

		int respuestaReal = mapa1.movePlayer(p1, 4);
		
		assertEquals(new Position(2, 5), p1.getPos().getPos());
		assertEquals(0, respuestaReal);

		// TIRA JUGADOR 2

		int respuestaReal2 = mapa1.movePlayer(p2, 8);
		
		assertEquals(new Position(3, 4), p2.getPos().getPos());
		assertEquals(2, respuestaReal2);

		// ELIGE JUGADOR 2

		int respuestaReal3 = mapa1.movePlayer(p2, 2, p2.getPos().nextNodes().get(0));

		assertEquals(new Position(2, 2), p2.getPos().getPos());
		assertEquals(0, respuestaReal3);

		// CHEQUEO QUE P2 TENGA 0 MONEDAS Y P1 0
		
		assertEquals(0, p2.getCoins());
		assertEquals(0, p1.getCoins());

		// TIRA JUGADOR 2

		int respuestaReal4 = mapa1.movePlayer(p2, 5);
		
		assertEquals(0, respuestaReal4);

		// CHECKEO QUE EL PLAYER 2 TENGA 3 MONEDAS

		assertEquals(3, p2.getCoins());
	}
}
