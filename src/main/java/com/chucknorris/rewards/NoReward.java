package com.chucknorris.rewards;

import com.chucknorris.player.Player;

import java.util.List;

public class NoReward implements RewardApplicable {

	@Override
	public void apply(Player playerExecutor, List<Player> players, GameContext context) {
		//Nothing to apply
	}

}
