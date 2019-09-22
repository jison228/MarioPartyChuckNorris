package com.chucknorris.player;

public class Player {
	private String character;
	private int coins;
	private int posX;
	private int posY;
	
	public Player(String character, int coins) {
		this.character = character;
		this.coins = coins; 
		this.posX = 0;
		this.posY = 0;
		//despues de avanzar en el proyecto podriamos setear las
		//monedas en el constructor a un valor fijo como 10 o 0
		//podriamos setearla aca en una posicion inicial como al principio del tablero
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
	
	public void setPos(int x, int y) {
		this.posX = x; 
		this.posY = y; 
	}
//por si queremos setear al jugador en areas distintas a la seteada en el constructor
//sin llamar a un metodo del mapa(por ahora solo para ser utilizada en testeos)	
	
	public int[] getPos() {
		return new int[] { this.posX, this.posY};
	}
	
	public String toString() {
		return this.character + " " + this.coins; //+ Nodo.toString();
	}
	
}
