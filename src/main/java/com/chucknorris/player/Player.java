package com.chucknorris.player;

import com.chucknorris.gamemap.nodes.Node;
import com.chucknorris.game.GameContext;

import java.util.List;

public class Player {
    private String character;
    private int coins;
	private Node nodeLocation;
	
	private double pesos;
	private double dolars;

    public Player(String character, int coins) {
        this.character = character;
        this.coins = coins;
		this.nodeLocation = null;
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

	public Node getNodeLocation() {
		return this.nodeLocation;
    }

	public void setNodeLocation(Node newPos) {
		this.nodeLocation = newPos;
    }

    public String toString() {
        return this.character + " " + this.coins; //+ Nodo.toString();
    }

	public void applyReward(List<Player> players, GameContext gameContext) {
		nodeLocation.applyReward(this, players, gameContext);
	}

	public void payUSD(double cant) {
		this.dolars-=cant;
	}	

	public void payPesos(double cant) {
		this.pesos-=cant;
	}	

	public void giveUSD(double cant) {
		this.dolars+=cant;
	}	

	public void givePesos(double cant) {
		this.pesos+=cant;
	}	

	public double getUSD() {
		return this.dolars;
	}	

	public double getPesos() {
		return this.pesos;
	}	
	
}
