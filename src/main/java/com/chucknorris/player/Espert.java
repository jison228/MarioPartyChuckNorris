package com.chucknorris.player;

import java.util.List;

import com.chucknorris.rewards.GameContext;
import com.chucknorris.rewards.substractor.dolar.DolarPercentagePlayerSubstractor;
import com.chucknorris.rewards.substractor.pesos.PesosPercentagePlayerSubstractor;

public class Espert extends Player {
	
	public Espert(double pesos, double dolares, double salario) {
		super("Espert", pesos, dolares, salario);
	}

	@Override
	public void applyPowerup(List<Player> players, GameContext gameContext) {
		double pesosP;
		double dolaresP;

		for (Player player : players) {
			player.substractCurrency(new PesosPercentagePlayerSubstractor(50));
			dolaresP = player.getDolares();
			pesosP = player.getPesos();
			player.substractCurrency(new PesosPercentagePlayerSubstractor(100));
			player.substractCurrency(new DolarPercentagePlayerSubstractor(100));
			player.addDolar(pesosP);
			player.addPesos(dolaresP);
		}
	}

	@Override
	public String getPowerupDescription() {
		return "Resta un 50% de los pesos de todos \nlos jugadores y los cambia con dólares\n\"QUIERO T*T*S\"";
	}

}
