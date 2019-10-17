package com.chucknorris.rewards;

import com.chucknorris.player.Player;

import java.util.List;

public class PercentagePesosPlayerAdder implements RewardApplicable {
	private double percentage;

	public PercentagePesosPlayerAdder(double percentage) {
		this.percentage = percentage;
	}

	@Override
	public void apply(Player playerExecutor, List<Player> players, GameContext context) {
		playerExecutor.addPesosByPercentage(percentage);
	}
}
