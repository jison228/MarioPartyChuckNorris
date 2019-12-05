package com.chucknorris.gamemap.nodes;

import java.util.List;

import com.chucknorris.commons.Position;
import com.chucknorris.rewards.substractor.pesos.PesosPercentagePlayersSubstractor;

public class AfipPesosNode extends Node {

    private static final int PERCENTAGE_TO_SUBSTRACT_OTHER_PLAYERS = 15;

    public AfipPesosNode(List<Node> next, Position positionCoords) {
        super(next, positionCoords, new PesosPercentagePlayersSubstractor(PERCENTAGE_TO_SUBSTRACT_OTHER_PLAYERS));
    }

    @Override
    public String getType() {
        return "AFIPP";
    }
}
