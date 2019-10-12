package com.chucknorris.player;

import com.chucknorris.gamemap.nodes.Node;
import com.chucknorris.rewards.GameContext;
import com.chucknorris.sustractor.pesos.PesosSubstractor;

import java.util.List;

public class Player {
    private String character;
    private int coins;
    private Node nodeLocation;
    private double pesos;

    public Player(String character, int coins) {
        this.character = character;
        this.coins = coins;
        //despues de avanzar en el proyecto podriamos setear las
        //monedas en el constructor a un valor fijo como 10 o 0
    }

    public Player(String character, double pesos) {
        this.character = character;
        this.pesos = pesos;
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

    public String printWithPesos() {
        return this.character + " " + this.pesos;
    }

    public void applyReward(List<Player> players, GameContext gameContext) {
        nodeLocation.applyReward(this, players, gameContext);
    }

    public void addPesos(double pesos) {
        this.pesos += pesos;
    }

    public void substractPesos(double pesos) {
        this.pesos -= pesos;
    }

    public double substractCurrency(PesosSubstractor percentagePesosPlayersSubstractor) {
        double pesosToSubstract = percentagePesosPlayersSubstractor.substract(pesos);

        substractPesos(pesosToSubstract);

        return pesosToSubstract;
    }
}
