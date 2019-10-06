package com.chucknorris.gamemap;

import com.chucknorris.commons.Position;
import com.chucknorris.player.Player;

import java.util.ArrayList;

public abstract class Node {
    protected ArrayList<Node> next;
    private Position pos;//utilizado para la matriz de nodos �nicamente

    public Node(ArrayList<Node> next, Position pos) {
        this.next = next;
        this.pos = pos;
    }

    public ArrayList<Node> nextNodes() {
        return next;
    }

    public Position getPos() {
        return pos;
    }

    public abstract void applyRewards(Player p);

    public abstract String getType();

}
