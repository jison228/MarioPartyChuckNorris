package com.chucknorris.player;

public class PlayerBuilder {
	private String character;
	private int coins;
	private double pesos;
	private double dolar;
	private double salario;
	private String id;

	public PlayerBuilder setCharacter(String character) {
		this.character = character;
		return this;
	}

	public PlayerBuilder setCoins(int coins) {
		this.coins = coins;
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

	public PlayerBuilder setId(String id) {
		this.id = id;
		return this;
	}

	public Player build() {
		return new Player(character, coins, pesos, dolar, salario, id);
	}
}
