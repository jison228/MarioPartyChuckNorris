package com.chucknorris.rewards.substractor.pesos;

import java.util.List;

import com.chucknorris.player.Player;
import com.chucknorris.rewards.GameContext;
import com.chucknorris.rewards.substractor.PercentagePlayersCurrencySubstractor;

public class PesosPercentagePlayerSubstractor extends PercentagePlayersCurrencySubstractor implements PesosSubstractor{

	public PesosPercentagePlayerSubstractor(int percentage) {
		super(percentage);
	}

	@Override
	public void apply(Player playerExecutor, List<Player> players, GameContext context) {
		playerExecutor.substractCurrency(this);
	}

}
