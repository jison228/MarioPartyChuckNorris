package com.chucknorris.player;

import com.chucknorris.mapa.Node;

public class Player {
	private String character;
	private int coins;
	private Node pos;
	
	public Player(String character , int coins) {
		this.character = character;
		this.coins = coins; 
		this.pos = null;
		//despues de avanzar en el proyecto podriamos setear las
		//monedas en el constructor a un valor fijo como 10 o 0
	}
	
	public int getCoins() {
		return coins;
	}
	
	public void addCoins(int coins) {
		this.coins = this.coins + coins;
	}

	public String getCharacter() {
		return this.character;
	}
	
	public void setPos(Node newPos ) {
		this.pos = newPos; 
	}
	
	public Node getPos() {
		return this.pos;
	}
	
	public String toString() {
		return this.character + " " + this.coins; //+ Nodo.toString();
	}
	
}
