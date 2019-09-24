package com.chucknorris.commons;

import java.util.ArrayList;
import com.chucknorris.mapa.Map;
import com.chucknorris.player.Player;

public class Game {
	ArrayList<Player> players;
	int turn;
	Map map;
	Dice dice = new Dice( 1 , 6 );
	//ArrayList de minijuegos
	
	public void main() {
		//este metodo lo controlara todo todito cuando sea grande, poner while
	}
	
	private void endTurn() {
		//verificar si alguien gano (por ahora no hay corte)
		turn++;
	}
	
	private int roll() {
		return dice.roll();
	}
	
	
}
