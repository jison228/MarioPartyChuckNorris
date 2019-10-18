package com.chucknorris.rewards;

import java.util.List;

import com.chucknorris.player.Player;

public class PercentageSalarioPlayerAdder implements RewardApplicable {

	private double percentage;

	public PercentageSalarioPlayerAdder(double percentage) {
		this.percentage = percentage;
	}

	@Override
	public void apply(Player playerExecutor, List<Player> players, GameContext context) {
		playerExecutor.addSalarioByPercentage(percentage);
	}

}
