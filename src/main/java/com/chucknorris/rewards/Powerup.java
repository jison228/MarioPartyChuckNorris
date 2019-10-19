package com.chucknorris.rewards;

import java.util.List;

import com.chucknorris.player.Player;

public class Powerup implements RewardApplicable {

	@Override
	public void apply(Player playerExecutor, List<Player> players, GameContext context) {
		playerExecutor.applyPowerup(players, context);
	}

}
