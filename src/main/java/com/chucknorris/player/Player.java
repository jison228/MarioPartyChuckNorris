package com.chucknorris.player;

import com.chucknorris.gamemap.nodes.Node;
import com.chucknorris.rewards.GameContext;
import com.chucknorris.rewards.substractor.dolar.DolarSubstractor;
import com.chucknorris.rewards.substractor.pesos.PesosSubstractor;

import java.util.List;

public class Player {
	private String playerID;
	private String character;
	private int coins;
	private Node nodeLocation;
	private double pesos;
	private double dolar;
	private double salario;

	Player(String character, double pesos, double dolar, double salario) {
		this.playerID = character;
		this.character = character;
		this.pesos = pesos;
		this.dolar = dolar;
		this.salario = salario;
	}
	
	Player(String character, String playerID, double pesos, double dolar, double salario) {
		this.playerID = playerID;
		this.character = character;
		this.pesos = pesos;
		this.dolar = dolar;
		this.salario = salario;
	}

	Player(String character, int coins, Node nodeLocation, double pesos, double dolar, double salario) {
		this.character = character;
		this.coins = coins;
		this.nodeLocation = nodeLocation;
		this.pesos = pesos;
		this.dolar = dolar;
		this.salario = salario;
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

	public void applyReward(List<Player> players, GameContext gameContext) {
		nodeLocation.applyReward(this, players, gameContext);
	}

	public void addPesos(double pesos) {
		this.pesos += pesos;
	}

	public void substractPesos(double pesos) {
		this.pesos = Math.max(0, this.pesos - pesos);
	}

	public void addDolar(double total) {
		this.dolar += total;
	}

	public void substractDolar(double dolar) {
		this.dolar = Math.max(0, this.dolar - dolar);
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

	public String printWithPesos() {
		return this.character + " " + (Math.round(this.pesos * 10) / 10.0);
	}
	
	public String printWithDolar() {
		return this.character + " " + (Math.round(this.dolar * 10) / 10.0);
	}
	
	public String printWithSalario() {
		return this.character + " " + (Math.round(this.salario * 10) / 10.0);
	}
	
	public void addPesosByPercentage(double percentage) {
		pesos = pesos * ((percentage / 100) + 1);
	}

	public double getPesos() {
		return Math.round(pesos * 10) / 10.0;
	}

	public double getDolares() {
		return Math.round(dolar * 10) / 10.0;
	}
	
	public double getSalario() {
		return Math.round(salario * 10) / 10.0;
	}
	
	public void addDolaresByPercentage(double percentage) {
		dolar = dolar * ((percentage / 100) + 1);
	}
	
	public void applyPowerup(List<Player> players, GameContext gameContext) {
		//DO NOTHING
	}
  
	public void addSalarioByPercentage(double percentage) {
		salario = salario * ((percentage / 100) + 1);
	}
	
	public void buyDolares(double pesos, double precioDolar) {
		this.substractPesos(pesos);
		this.addDolar(pesos/precioDolar);
	}
	
	public void cobrarSalario() {
		this.addPesos(salario);
	}
	
	public String getPowerupDescription() {
		return "";
	}

	public String printPlayerName() {
		return "Pepito";
	}
	
	public String getPlayerID() {
		return playerID;
	}
}
