package com.chucknorris.rewards;

import com.chucknorris.player.Player;
import com.chucknorris.game.GameContext;

import java.util.List;

public interface RewardApplicable {
	void apply(Player playerExecutor, List<Player> players, GameContext context);
}
