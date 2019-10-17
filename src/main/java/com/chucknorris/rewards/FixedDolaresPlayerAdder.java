package com.chucknorris.rewards;

import java.util.List;

import com.chucknorris.player.Player;

public class FixedDolaresPlayerAdder implements RewardApplicable {

	private double amount;

	public FixedDolaresPlayerAdder(double amount) {
		this.amount = amount;
	}

	@Override
	public void apply(Player playerExecutor, List<Player> players, GameContext context) {
		playerExecutor.addDolar(amount);
	}

}
