package com.chucknorris.player;

import java.util.List;

import com.chucknorris.rewards.GameContext;

public class Cristina extends Player{

	public Cristina(double pesos, double dolar, double salario) {
		super("Cristina", pesos, dolar, salario);
	}
	
	@Override
	public void applyPowerup(List<Player> players, GameContext gameContext) {
		double pesosMax = 0;
		for(Player player : players) {
			if(player.getPesos()>pesosMax) {
				pesosMax = player.getPesos();
			}
		}
		
		for(Player player : players) {
			if(player.getPesos()<pesosMax) {
				player.addPesos(pesosMax*0.30);
			}
		}
	}
	
	@Override
	public String getPowerupDescription() {
		return "Regala planes sociales a los \nmás pobres (30% del más rico)\n\"MACRI GATO\"";
	}

}
