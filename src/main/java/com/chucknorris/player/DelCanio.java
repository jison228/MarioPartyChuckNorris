package com.chucknorris.player;

import java.util.List;

import com.chucknorris.rewards.GameContext;

public class DelCanio extends Player {

	public DelCanio(double pesos, double dolares, double salario) {
		super("Del Caño", pesos, dolares, salario);
	}
	
	@Override
	public void applyPowerup(List<Player> players, GameContext gameContext) {
		double montoTotal = 0;

		for (Player player : players) {
			montoTotal += player.getPesos();
			player.substractPesos(player.getPesos());
		}

		for (Player player : players) {
			player.addPesos(montoTotal / players.size());
		}
	}

	@Override
	public String getPowerupDescription() {
		return "Divide los pesos de todos de forma equitativa\nÂ¡VIVA EL COMUNISMO!";
	}
}
