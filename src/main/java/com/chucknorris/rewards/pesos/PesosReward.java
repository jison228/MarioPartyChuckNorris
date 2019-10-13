package com.chucknorris.rewards.pesos;

import java.util.List;

import com.chucknorris.player.Player;
import com.chucknorris.rewards.GameContext;
import com.chucknorris.rewards.RewardApplicable;

public class PesosReward implements RewardApplicable{
	private double porcentajePesos;
	
	public PesosReward(double porcentajePesos) {
		this.porcentajePesos = porcentajePesos;
	}
	
	@Override
	public void apply(Player playerExecutor, List<Player> players, GameContext context) {
		playerExecutor.addPesosByPercentage(porcentajePesos);
	}
}
