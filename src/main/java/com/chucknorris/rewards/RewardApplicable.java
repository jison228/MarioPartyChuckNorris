package com.chucknorris.rewards;

import com.chucknorris.player.Player;

import java.util.List;

public interface RewardApplicable {
	void apply(Player playerExecutor, List<Player> player, GameContext context);
}
