package com.chucknorris.player;

import java.util.List;

import com.chucknorris.rewards.GameContext;

public class Macri extends Player {

	public Macri(double pesos, double dolar, double salario) {
		super("Macri", pesos, dolar, salario);
	}
	
	public Macri(String playerID, double pesos, double dolar, double salario) {
		super("Macri", playerID, pesos, dolar, salario);
	}
	
	@Override
	public void applyPowerup(List<Player> players, GameContext gameContext) {
		double dolaresMax = 0;
		double dolaresCasiMax = 0;
		for (Player player : players) {
			if (player.getDolares() > dolaresMax) {
				dolaresCasiMax = dolaresMax;
				dolaresMax = player.getDolares();
			}
		}

		for (Player player : players) {
			if (player.getDolares() >= dolaresCasiMax) {
				player.addDolaresByPercentage(20);
			}
		}
	}
	
	@Override
	public String getPowerupDescription() {
		return "Aumenta los dolares de los dos \njugadores mas ricos un 20%\n\"Miau\"";
	}
}
