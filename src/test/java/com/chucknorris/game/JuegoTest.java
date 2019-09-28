package com.chucknorris.game;

import com.chucknorris.commons.Game;
import com.chucknorris.commons.Position;
import com.chucknorris.mapa.*;
import com.chucknorris.player.Player;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class JuegoTest {
	@Test
	public void test() {

		Position pNodoEnd = new Position(1, 0);
		EndNode nodoEnd = new EndNode(pNodoEnd);

		Position pNodo15 = new Position(2, 0);
		ArrayList<Node> nodo15Nexts = new ArrayList<Node>();
		nodo15Nexts.add(nodoEnd);
		YellowNode nodo15 = new YellowNode(nodo15Nexts, pNodo15);

		Position pNodo14 = new Position(3, 0);
		ArrayList<Node> nodo14Nexts = new ArrayList<Node>();
		nodo14Nexts.add(nodo15);
		YellowNode nodo14 = new YellowNode(nodo14Nexts, pNodo14);

		Position pNodo13 = new Position(3, 1);
		ArrayList<Node> nodo13Nexts = new ArrayList<Node>();
		nodo13Nexts.add(nodo14);
		WhiteNode nodo13 = new WhiteNode(nodo13Nexts, pNodo13);

		Position pNodo12 = new Position(3, 2);
		ArrayList<Node> nodo12Nexts = new ArrayList<Node>();
		nodo12Nexts.add(nodo13);
		WhiteNode nodo12 = new WhiteNode(nodo12Nexts, pNodo12);

		Position pNodo11 = new Position(3, 3);
		ArrayList<Node> nodo11Nexts = new ArrayList<Node>();
		nodo11Nexts.add(nodo12);
		WhiteNode nodo11 = new WhiteNode(nodo11Nexts, pNodo11);

		Position pNodo10 = new Position(2, 1);
		ArrayList<Node> nodo10Nexts = new ArrayList<Node>();
		nodo10Nexts.add(nodo14);
		WhiteNode nodo10 = new WhiteNode(nodo10Nexts, pNodo10);

		Position pNodo9 = new Position(2, 2);
		ArrayList<Node> nodo9Nexts = new ArrayList<Node>();
		nodo9Nexts.add(nodo10);
		WhiteNode nodo9 = new WhiteNode(nodo9Nexts, pNodo9);

		Position pNodo8 = new Position(2, 3);
		ArrayList<Node> nodo8Nexts = new ArrayList<Node>();
		nodo8Nexts.add(nodo9);
		RedNode nodo8 = new RedNode(nodo8Nexts, pNodo8);

		Position pNodo7 = new Position(3, 4);
		ArrayList<Node> nodo7Nexts = new ArrayList<Node>();
		nodo7Nexts.add(nodo8);
		nodo7Nexts.add(nodo11);
		YellowNode nodo7 = new YellowNode(nodo7Nexts, pNodo7);

		Position pNodo6 = new Position(3, 5);
		ArrayList<Node> nodo6Nexts = new ArrayList<Node>();
		nodo6Nexts.add(nodo7);
		WhiteNode nodo6 = new WhiteNode(nodo6Nexts, pNodo6);

		Position pNodo5 = new Position(2, 5);
		ArrayList<Node> nodo5Nexts = new ArrayList<Node>();
		nodo5Nexts.add(nodo6);
		WhiteNode nodo5 = new WhiteNode(nodo5Nexts, pNodo5);

		Position pNodo4 = new Position(0, 5);
		ArrayList<Node> nodo4Nexts = new ArrayList<Node>();
		nodo4Nexts.add(nodo5);
		YellowNode nodo4 = new YellowNode(nodo4Nexts, pNodo4);

		Position pNodo3 = new Position(0, 3);
		ArrayList<Node> nodo3Nexts = new ArrayList<Node>();
		nodo3Nexts.add(nodo4);
		WhiteNode nodo3 = new WhiteNode(nodo3Nexts, pNodo3);

		Position pNodo2 = new Position(0, 1);
		ArrayList<Node> nodo2Nexts = new ArrayList<Node>();
		nodo2Nexts.add(nodo3);
		WhiteNode nodo2 = new WhiteNode(nodo2Nexts, pNodo2);

		Position pNodo1 = new Position(0, 0);
		ArrayList<Node> nodo1Nexts = new ArrayList<Node>();
		nodo1Nexts.add(nodo2);
		RedNode nodo1 = new RedNode(nodo1Nexts, pNodo1);

		nodoEnd.setStart(nodo1);
		ArrayList<Node> nodos = new ArrayList<Node>();
		nodos.add(nodo1);
		nodos.add(nodo2);
		nodos.add(nodo3);
		nodos.add(nodo4);
		nodos.add(nodo5);
		nodos.add(nodo6);
		nodos.add(nodo7);
		nodos.add(nodo8);
		nodos.add(nodo9);
		nodos.add(nodo10);
		nodos.add(nodo11);
		nodos.add(nodo12);
		nodos.add(nodo13);
		nodos.add(nodo14);
		nodos.add(nodo15);
		nodos.add(nodoEnd);

		Map mapa1 = new Map(nodos, nodo1);
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

		Response respuestaReal = mapa1.movePlayer(p1, 4);
		Position pesperada = new Position(2, 5);
		Response rEsperada = new Response(nodo5, null, 0);
		assertEquals(pesperada, p1.getPos().getPos());
		assertEquals(rEsperada, respuestaReal);

		// TIRA JUGADOR 2

		Response respuestaReal2 = mapa1.movePlayer(p2, 8);
		Position pesperada2 = new Position(3, 4);
		Position pesperadaDisp1 = new Position(2, 3);
		Position pesperadaDisp2 = new Position(3, 3);
		ArrayList<Position> arrayPosicionesDisponibles2 = new ArrayList<Position>();
		arrayPosicionesDisponibles2.add(pesperadaDisp1);
		arrayPosicionesDisponibles2.add(pesperadaDisp2);
		Response rEsperada2 = new Response(nodo7, arrayPosicionesDisponibles2, 2);
		assertEquals(pesperada2, p2.getPos().getPos());
		assertEquals(rEsperada2, respuestaReal2);

		// ELIGE JUGADOR 2

		Response respuestaReal3 = mapa1.movePlayer(p2, 2, nodo8);
		Position pesperada3 = new Position(2, 2);
		Response rEsperada3 = new Response(nodo9, null, 0);
		assertEquals(pesperada3, p2.getPos().getPos());
		assertEquals(rEsperada3, respuestaReal3);

		// CHEQUEO QUE P2 TENGA 0 MONEDAS Y P1 0
		assertEquals(0, p2.getCoins());
		assertEquals(0, p1.getCoins());

		// TIRA JUGADOR 2

		Response respuestaReal4 = mapa1.movePlayer(p2, 5);
	}
}
