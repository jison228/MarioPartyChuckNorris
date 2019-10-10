package com.chucknorris.gamemap.nodes;

import com.chucknorris.commons.Position;
import com.chucknorris.player.Player;

import java.util.ArrayList;

public class RedNode extends Node {

    public RedNode(ArrayList<Node> next, Position pos) {
        super(next, pos);
    }

    @Override
    public void applyRewards(Player p) {
        p.addCoins(3);
    }

    @Override
    public String getType() {
        return "RED";
    }

}
