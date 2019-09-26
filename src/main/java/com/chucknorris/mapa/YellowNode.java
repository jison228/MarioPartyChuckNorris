package com.chucknorris.mapa;

import com.chucknorris.commons.Position;
import com.chucknorris.player.Player;

import java.util.ArrayList;

public class YellowNode extends Node {

    public YellowNode(ArrayList<Node> next, Position pos) {
        super(next, pos);
    }

    @Override
    public void applyRewards(Player p) {
        p.addCoins(10);
    }

    @Override
    public String getType() {
        return "YELLOW";
    }

}
