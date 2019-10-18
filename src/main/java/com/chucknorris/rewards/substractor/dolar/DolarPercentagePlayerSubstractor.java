package com.chucknorris.rewards.substractor.dolar;

import java.util.List;

import com.chucknorris.player.Player;
import com.chucknorris.rewards.GameContext;
import com.chucknorris.rewards.substractor.PercentagePlayersCurrencySubstractor;

public class DolarPercentagePlayerSubstractor extends PercentagePlayersCurrencySubstractor implements DolarSubstractor {

	public DolarPercentagePlayerSubstractor(int percentage) {
		super(percentage);
	}

	@Override
	public void apply(Player playerExecutor, List<Player> players, GameContext context) {
		playerExecutor.substractCurrency(this);
	}
}