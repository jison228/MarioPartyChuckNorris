package com.chucknorris.gamemap.nodes;

import com.chucknorris.commons.Position;
import com.chucknorris.rewards.substractor.pesos.PesosPercentagePlayersSubstractor;

import java.util.ArrayList;

public class AfipPesosNode extends Node {

    private static final int PERCENTAGE_TO_SUBSTRACT_OTHER_PLAYERS = 10;

    public AfipPesosNode(ArrayList<Node> next, Position positionCoords) {
        super(next, positionCoords, new PesosPercentagePlayersSubstractor(PERCENTAGE_TO_SUBSTRACT_OTHER_PLAYERS));
    }

    @Override
    public String getType() {
        return "AFIPP";
    }
}
