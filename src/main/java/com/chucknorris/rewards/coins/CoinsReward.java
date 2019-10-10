package com.chucknorris.rewards.coins;

import com.chucknorris.player.Player;
import com.chucknorris.rewards.GameContext;
import com.chucknorris.rewards.RewardApplicable;

import java.util.List;

public class CoinsReward implements RewardApplicable {

	private int coins;

	public CoinsReward(int coins) {
		this.coins = coins;
	}

	@Override
	public void apply(Player playerExecutor, List<Player> player, GameContext context) {
		playerExecutor.addCoins(coins);
	}
}
