package com.chucknorris.rewards;

import java.util.List;

import com.chucknorris.player.Player;

public class PercentageDolaresPlayerAdder implements RewardApplicable {

	private double percentage;

	public PercentageDolaresPlayerAdder(double percentage) {
		this.percentage = percentage;
	}

	@Override
	public void apply(Player playerExecutor, List<Player> players, GameContext context) {
		playerExecutor.addDolaresByPercentage(percentage);
	}

}
