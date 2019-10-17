package com.chucknorris.rewards.substractor.dolar;

import com.chucknorris.player.Player;
import com.chucknorris.rewards.GameContext;
import com.chucknorris.rewards.substractor.PercentagePlayersCurrencySubstractor;

import java.util.List;

public class DolarPercentagePlayersSubstractor extends PercentagePlayersCurrencySubstractor implements DolarSubstractor {

	public DolarPercentagePlayersSubstractor(int percentage) {
		super(percentage);
	}

	@Override
	public void apply(Player playerExecutor, List<Player> players, GameContext context) {
		double dolar = 0;

		for (Player player : players) {
			dolar += player.substractCurrency(this);
		}

		playerExecutor.addDolar(dolar);
	}
}
