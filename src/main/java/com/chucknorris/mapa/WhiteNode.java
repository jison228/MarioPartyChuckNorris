package com.chucknorris.mapa;

import com.chucknorris.commons.Position;
import com.chucknorris.player.Player;

import java.util.ArrayList;

public class WhiteNode extends Node {

    public WhiteNode(ArrayList<Node> next, Position pos) {
        super(next, pos);
    }

    @Override
    public void applyRewards(Player p) {
        //no hace nada
    }

    @Override
    public String getType() {
        return "WHITE";
    }

}
