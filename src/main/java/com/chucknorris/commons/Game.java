package com.chucknorris.commons;

import java.util.ArrayList;
import com.chucknorris.mapa.Map;
import com.chucknorris.player.Player;

public class Game {
	private ArrayList<Player> players;
	private int turn;
	private Map map;
	private Dice dice;
	//ArrayList de minijuegos
	
	private void endTurn() {
		//verificar si alguien gano (por ahora no hay corte)
		turn++;
	}
	
	public Game(ArrayList<Player> players, Map map) {
		this.players = players;
		this.turn = 1;
		this.map = map;
		this.dice = new Dice( 1 , 6 );
	}

	private int roll() {
		return dice.roll();
	}
	
	
}
