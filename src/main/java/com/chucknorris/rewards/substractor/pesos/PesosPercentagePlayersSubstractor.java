package com.chucknorris.rewards.substractor.pesos;

import com.chucknorris.player.Player;
import com.chucknorris.rewards.GameContext;
import com.chucknorris.rewards.substractor.PercentagePlayersCurrencySubstractor;

import java.util.List;

public class PesosPercentagePlayersSubstractor extends PercentagePlayersCurrencySubstractor implements PesosSubstractor {

    public PesosPercentagePlayersSubstractor(int percentage) {
        super(percentage);
    }

    @Override
    public void apply(Player playerExecutor, List<Player> players, GameContext context) {
        double pesos = 0;

        for (Player player : players) {
            pesos += player.substractCurrency(this);
        }

        playerExecutor.addPesos(pesos);
    }
}