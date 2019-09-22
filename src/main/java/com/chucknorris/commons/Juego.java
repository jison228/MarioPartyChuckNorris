package com.chucknorris.commons;

import java.util.ArrayList;

import com.chucknorris.mapa.Map;
import com.chucknorris.player.Player;

public class Juego {
	ArrayList<Player> players;
	int turno;
	Map mapa;
	Dice dice;
	//ArrayList de minijuegos
	
	public void main() {
		//este metodo lo controlara todo todito cuando sea grande, poner while
	}
	
	private void endTurn() {
		//verificar si alguien gano (por ahora no hay corte)
		turno++;
	}
	
	
}
