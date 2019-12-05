package com.chucknorris.player;

import com.chucknorris.gamemap.nodes.Node;

public class PlayerBuilder {
	private String character;
	private int coins;
	private Node nodeLocation;
	private double pesos;
	private double dolar;
	private double salario;

	public PlayerBuilder setCharacter(String character) {
		this.character = character;
		return this;
	}

	public PlayerBuilder setCoins(int coins) {
		this.coins = coins;
		return this;
	}

	public PlayerBuilder setNodeLocation(Node nodeLocation) {
		this.nodeLocation = nodeLocation;
		return this;
	}

	public PlayerBuilder setPesos(double pesos) {
		this.pesos = pesos;
		return this;
	}

	public PlayerBuilder setDolar(double dolar) {
		this.dolar = dolar;
		return this;
	}

	public PlayerBuilder setSalario(double salario) {
		this.salario = salario;
		return this;
	}

	public Player build() {
		return new Player(character, coins, nodeLocation, pesos, dolar, salario);
	}
}
