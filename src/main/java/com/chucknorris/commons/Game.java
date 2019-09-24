package com.chucknorris.commons;

import java.util.ArrayList;
import com.chucknorris.mapa.Map;
import com.chucknorris.player.Player;

public class Game {
	private ArrayList<Player> players;
	private int turn = 1;
	private Map map;
	private Dice dice = new Dice( 1 , 6 );
	//ArrayList de minijuegos
	
	private void endTurn() {
		//verificar si alguien gano (por ahora no hay corte)
		turn++;
	}
	
	public Game(ArrayList<Player> players, Map map) {
		this.players = players;
		this.map = map;
	}

	private int roll() {
		return dice.roll();
	}
	
	
}
