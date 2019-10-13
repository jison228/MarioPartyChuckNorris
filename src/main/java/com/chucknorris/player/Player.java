package com.chucknorris.player;

import com.chucknorris.gamemap.nodes.Node;
import com.chucknorris.rewards.GameContext;
import com.chucknorris.rewards.substractor.dolar.DolarSubstractor;
import com.chucknorris.rewards.substractor.pesos.PesosSubstractor;

import java.util.List;

public class Player {
	private String character;
	private int coins;
	private Node nodeLocation;
	private double pesos;
	private double dolar;

	public Player(String character, int coins) {
		this.character = character;
		this.coins = coins;
		// despues de avanzar en el proyecto podriamos setear las
		// monedas en el constructor a un valor fijo como 10 o 0
	}

	public Player(String character, double pesos) {
		this.character = character;
		this.pesos = pesos;
	}

	public Player(String character, double pesos, double dolar) {
		this.character = character;
		this.pesos = pesos;
		this.dolar = dolar;
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
		return this.character + " " + this.coins; // + Nodo.toString();
	}

	public String printWithPesos() {
		return this.character + " " + this.pesos;
	}

	public void applyReward(List<Player> players, GameContext gameContext) {
		nodeLocation.applyReward(this, players, gameContext);
	}

	public double addPesos(double pesos) {
		this.pesos += pesos;
		return pesos;
	}

	public void substractPesos(double pesos) {
		this.pesos -= pesos;
	}

	public void addDolar(double total) {
		this.dolar += total;
	}

	public void substractDolar(double dolar) {
		this.dolar -= dolar;
	}

	public double substractCurrency(PesosSubstractor substractor) {
		double pesosToSubstract = substractor.substract(pesos);

		substractPesos(pesosToSubstract);

		return pesosToSubstract;
	}

	public double substractCurrency(DolarSubstractor substractor) {
		double dolarToSubstract = substractor.substract(dolar);

		substractDolar(dolarToSubstract);

		return dolarToSubstract;
	}

	public String printWithDolar() {
		return this.character + " " + this.dolar;
	}

	public void addPesosByPercentage(double percentage) {
		pesos = pesos * ((percentage / 100) + 1);
	}

	public double getPesos() {
		return pesos;
	}
}
