package com.chucknorris.player;

import java.util.List;

import com.chucknorris.rewards.GameContext;

public class Macri extends Player{

	public Macri(double pesos, double dolar) {
		super("Macri", pesos, dolar);
	}
	
	public void applyPowerup(List<Player> players, GameContext gameContext) {
		double dolaresMax = 0;
		double dolaresCasiMax = 0;
		for(Player player : players) {
			if(player.getDolares()>dolaresMax) {
				dolaresCasiMax = dolaresMax;
				dolaresMax = player.getDolares();
			}
		}
		
		for(Player player : players) {
			if(player.getDolares()>=dolaresCasiMax) {
				//player.addDolaresByPercentage(30);
			}
		}
	}
	
	public String getPowerupDescription() {
		return "Aumenta los dólares de los dos jugadores más ricos un 20%\nMiau";
	}
}
