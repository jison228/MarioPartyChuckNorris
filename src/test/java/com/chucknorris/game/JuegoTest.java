package com.chucknorris.game;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import com.chucknorris.commons.Juego;
import com.chucknorris.commons.Position;
import com.chucknorris.mapa.Map;
import com.chucknorris.mapa.Node;
import com.chucknorris.mapa.NodeYellow;
import com.chucknorris.mapa.NodeEnd;
import com.chucknorris.mapa.NodeRed;
import com.chucknorris.mapa.NodeWhite;
import com.chucknorris.mapa.Response;
import com.chucknorris.player.Player;

public class JuegoTest {
	@Test
	 public void test() {
	       Juego juego1 = new Juego();
	       
	       Position pNodoEnd = new Position(1,0);
	       ArrayList<Node> nodoEndNexts = new ArrayList<Node>();
	       NodeEnd nodoEnd = new NodeEnd(pNodoEnd);
	       
	       Position pNodo15 = new Position(2,0);
	       ArrayList<Node> nodo15Nexts = new ArrayList<Node>();
	       nodo15Nexts.add(nodoEnd);
	       NodeYellow nodo15 = new NodeYellow(nodo15Nexts, pNodo15);
	       
	       Position pNodo14 = new Position(3,0);
	       ArrayList<Node> nodo14Nexts = new ArrayList<Node>();
	       nodo14Nexts.add(nodo15);
	       NodeYellow nodo14 = new NodeYellow(nodo14Nexts, pNodo14);
	       
	       Position pNodo13 = new Position(3,1);
	       ArrayList<Node> nodo13Nexts = new ArrayList<Node>();
	       nodo13Nexts.add(nodo14);
	       NodeWhite nodo13 = new NodeWhite(nodo13Nexts, pNodo13);
	       
	       Position pNodo12 = new Position(3,2);
	       ArrayList<Node> nodo12Nexts = new ArrayList<Node>();
	       nodo12Nexts.add(nodo13);
	       NodeWhite nodo12 = new NodeWhite(nodo12Nexts, pNodo12);
	       
	       Position pNodo11 = new Position(3,3);
	       ArrayList<Node> nodo11Nexts = new ArrayList<Node>();
	       nodo11Nexts.add(nodo12);
	       NodeWhite nodo11 = new NodeWhite(nodo11Nexts, pNodo11);
	       
	       Position pNodo10 = new Position(2,1);
	       ArrayList<Node> nodo10Nexts = new ArrayList<Node>();
	       nodo10Nexts.add(nodo14);
	       NodeWhite nodo10 = new NodeWhite(nodo10Nexts, pNodo10);
	       
	       Position pNodo9 = new Position(2,2);
	       ArrayList<Node> nodo9Nexts = new ArrayList<Node>();
	       nodo9Nexts.add(nodo10);
	       NodeWhite nodo9 = new NodeWhite(nodo9Nexts, pNodo9);
	       
	       Position pNodo8 = new Position(2,3);
	       ArrayList<Node> nodo8Nexts = new ArrayList<Node>();
	       nodo8Nexts.add(nodo9);
	       NodeRed nodo8 = new NodeRed(nodo8Nexts, pNodo8);
	       
	       Position pNodo7 = new Position(3,4);
	       ArrayList<Node> nodo7Nexts = new ArrayList<Node>();
	       nodo7Nexts.add(nodo8);
	       nodo7Nexts.add(nodo11);
	       NodeYellow nodo7 = new NodeYellow(nodo7Nexts, pNodo7);
	       
	       Position pNodo6 = new Position(3,5);
	       ArrayList<Node> nodo6Nexts = new ArrayList<Node>();
	       nodo6Nexts.add(nodo7);
	       NodeWhite nodo6 = new NodeWhite(nodo6Nexts, pNodo6);
	       
	       Position pNodo5 = new Position(2,5);
	       ArrayList<Node> nodo5Nexts = new ArrayList<Node>();
	       nodo5Nexts.add(nodo6);
	       NodeWhite nodo5 = new NodeWhite(nodo5Nexts, pNodo5);
	       
	       Position pNodo4 = new Position(0,5);
	       ArrayList<Node> nodo4Nexts = new ArrayList<Node>();
	       nodo4Nexts.add(nodo5);
	       NodeYellow nodo4 = new NodeYellow(nodo4Nexts, pNodo4);
	       
	       Position pNodo3 = new Position(0,3);
	       ArrayList<Node> nodo3Nexts = new ArrayList<Node>();
	       nodo3Nexts.add(nodo4);
	       NodeWhite nodo3 = new NodeWhite(nodo3Nexts, pNodo3);
	       
	       Position pNodo2 = new Position(0,1);
	       ArrayList<Node> nodo2Nexts = new ArrayList<Node>();
	       nodo2Nexts.add(nodo3);
	       NodeWhite nodo2 = new NodeWhite(nodo2Nexts, pNodo2);
	       
	       Position pNodo1 = new Position(0,0);
	       ArrayList<Node> nodo1Nexts = new ArrayList<Node>();
	       nodo1Nexts.add(nodo2);
	       NodeRed nodo1 = new NodeRed(nodo1Nexts, pNodo1);
	       
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
	       
	       Map mapa1 = new Map(nodos,nodo1);
	       // MAPA CREADO
	       
	       Player p1 = new Player("Javier Milei",0);
	       Player p2 = new Player("MauriCEOMcree",0);
	       ArrayList<Player> playerList = new ArrayList<Player>();
	       playerList.add(p1);
	       playerList.add(p2);
	       mapa1.initializePlayers(playerList);
	       // LISTO PARA JUGAR
	       
	       //TIRA JUGADOR 1
	      
	       Response respuestaReal = mapa1.mover(p1, 4);
	       Position pesperada = new Position(2,5);
	       Position pesperadaDisp = new Position(3,5);

	       ArrayList<Position> arrayPosicionesDisponibles = new ArrayList<Position>();
	       arrayPosicionesDisponibles.add(pesperadaDisp);
	       Response rEsperada = new Response(pesperada,"WHITE",arrayPosicionesDisponibles,0);
	       assertEquals(pesperada, p1.getPos());
	       assertEquals(rEsperada, respuestaReal);
	       
	       //TIRA JUGADOR 2
	       
	       Response respuestaReal2 = mapa1.mover(p2, 7);
	       Position pesperada2 = new Position(3,4);
	       Position pesperadaDisp1 = new Position(2,3);
	       Position pesperadaDisp2 = new Position(3,3);
	       ArrayList<Position> arrayPosicionesDisponibles2 = new ArrayList<Position>();
	       arrayPosicionesDisponibles2.add(pesperadaDisp1);
	       arrayPosicionesDisponibles2.add(pesperadaDisp2);
	       Response rEsperada2 = new Response(pesperada2,"AMARILLO",arrayPosicionesDisponibles2,1);
	       assertEquals(pesperada2, p2.getPos());
	       assertEquals(rEsperada2, respuestaReal2);
	       
	       //ELIGE JUGADOR 2
	       
	       Position posicionElegida = new Position(2, 3);
	       
	       Response respuestaReal3 = mapa1.mover(p2,1,posicionElegida);
	       Position pesperada3 = new Position(2,3);
	       ArrayList<Position> arrayPosicionesDisponibles3 = new ArrayList<Position>();
	       Position pesperadaDisp3 = new Position(3,2);
	       arrayPosicionesDisponibles3.add(pesperadaDisp3);
	       Response rEsperada3 = new Response(pesperada3,"ROJO",arrayPosicionesDisponibles3,0);
	       assertEquals(pesperada3, p2.getPos());
	       assertEquals(rEsperada3, respuestaReal3);
	       
	       //CHEQUEO QUE P2 TENGA 2 MONEDAS Y P1 0
	       assertEquals(3,p2.getCoins());
	       assertEquals(0,p1.getCoins());
	    }
}
