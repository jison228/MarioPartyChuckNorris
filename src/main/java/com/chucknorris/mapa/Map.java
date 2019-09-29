package com.chucknorris.mapa;

import com.chucknorris.commons.Position;
import com.chucknorris.player.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class Map {
    private HashMap<Position, Node> nodes;
    private Node start;

    //Pendiente de modificar cuando se lea el mapa mediante un .txt
    public Map(ArrayList<Node> nodes, Node start) {
        this.nodes = new HashMap<Position, Node>();
        for (Node node : nodes) {
            this.nodes.put(node.getPos(), node);
        }
        this.start = start;
    }

    public void initializePlayers(ArrayList<Player> players) {
        for (Player p : players) {
            p.setPos(start);
        }
    }

    public int movePlayer(Player p, int movs) {
        Node node = nodes.get(p.getPos().getPos());
        int leftMovs = movs;
        while (node.nextNodes().size() == 1 && leftMovs > 0) {
            node = node.nextNodes().get(0);
            leftMovs--;
        }
        p.setPos(node);

        if (leftMovs == 0) {
        	node.applyRewards(p);
        }

        return leftMovs;
    }

    public int movePlayer(Player p, int movs, Node pos) {
        p.setPos(pos);
        return movePlayer(p, movs - 1);
    }


}