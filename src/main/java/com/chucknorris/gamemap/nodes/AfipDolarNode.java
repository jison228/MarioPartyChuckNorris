package com.chucknorris.gamemap.nodes;

import java.util.List;

import com.chucknorris.commons.Position;
import com.chucknorris.rewards.substractor.dolar.DolarPercentagePlayersSubstractor;

public class AfipDolarNode extends Node {

    private static final int PERCENTAGE_TO_SUBSTRACT = 5;

    public AfipDolarNode(List<Node> next, Position positionCoords) {
        super(next, positionCoords, new DolarPercentagePlayersSubstractor(PERCENTAGE_TO_SUBSTRACT));
    }

    @Override
    public String getType() {
        return "AFIPD";
    }
}
