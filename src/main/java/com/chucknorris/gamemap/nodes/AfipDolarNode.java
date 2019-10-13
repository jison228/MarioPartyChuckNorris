package com.chucknorris.gamemap.nodes;

import com.chucknorris.commons.Position;
import com.chucknorris.rewards.substractor.dolar.DolarPercentagePlayersSubstractor;

import java.util.ArrayList;

public class AfipDolarNode extends Node {

    private static final int PERCENTAGE_TO_SUBSTRACT = 2;

    public AfipDolarNode(ArrayList<Node> next, Position positionCoords) {
        super(next, positionCoords, new DolarPercentagePlayersSubstractor(PERCENTAGE_TO_SUBSTRACT));
    }

    @Override
    public String getType() {
        return "AFIPD";
    }
}
