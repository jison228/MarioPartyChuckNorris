package com.chucknorris.rewards.substractor.pesos;

import com.chucknorris.player.Player;
import com.chucknorris.rewards.GameContext;

import java.util.List;

public class PercentagePesosPlayersSubstractor implements PesosSubstractor {

    private double percentage;

    public PercentagePesosPlayersSubstractor(int percentage) {
        this.percentage = percentage;
    }

    @Override
    public void apply(Player playerExecutor, List<Player> players, GameContext context) {
        double pesos = 0;

        for (Player player : players) {
            pesos += player.substractCurrency(this);
        }

        playerExecutor.addPesos(pesos);
    }

    @Override
    public double substract(double amountToBeSubstract) {
        return amountToBeSubstract * (percentage / 100);
    }
}
