package com.chucknorris.rewards;

import com.chucknorris.player.Player;

import java.util.List;

public class FixedPesosPlayerAdder implements RewardApplicable {
    private double amount;

    public FixedPesosPlayerAdder(double amount) {
        this.amount = amount;
    }

    @Override
    public void apply(Player playerExecutor, List<Player> players, GameContext context) {
        playerExecutor.addPesos(amount);
    }
}
