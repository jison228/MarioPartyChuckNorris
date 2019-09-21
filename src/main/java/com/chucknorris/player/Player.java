package com.chucknorris.player;

public class Player {
	private String character;
	private int coins;
	//private Nodo position; // clase Nodo todavia no creada
	
	public Player(String character, int coins) {//Nodo pos
		this.character = character;
		this.coins = coins; 
		//despues de avanzar en el proyecto podriamos setear las
		//monedas en el constructor a un valor fijo como 10 o 0
		///this.position = pos; 
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
	
/*	public void setPos(Nodo pos) {
		this.position = pos; 
	}
*/ 
//por si queremos setear al jugador en areas distintas a la seteada en el constructor
//sin llamar a un metodo del mapa(por ahora solo para ser utilizada en testeos)	
	
/*	public Nodo getPos() {
		return this.position;
	}
*/
	
	public String toString() {
		return this.character + " " + this.coins; //+ Nodo.toString();
	}
	
}
