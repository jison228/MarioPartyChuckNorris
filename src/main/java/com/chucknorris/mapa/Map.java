package com.chucknorris.mapa;

import com.chucknorris.commons.Position;
import com.chucknorris.player.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Map {
    private HashMap<Position, Node> nodes;
    private Node start;

    public Map(String tablero) throws FileNotFoundException {
    	Scanner sc = new Scanner(new File("maps/" + tablero));
    	this.nodes = new HashMap<Position , Node>();
    	
    	int X , Y;
    	X = sc.nextInt();
    	Y = sc.nextInt();
    	Position posEnd = new Position(X , Y);
    	
    	EndNode nodeEnd = new EndNode(posEnd);
    	nodes.put(posEnd , nodeEnd);
    	
    	ArrayList<Node> next = null;
    	Node node = null;
    	while(sc.hasNext()) {
    		X = sc.nextInt();
    		Y = sc.nextInt();
    		Position pos = new Position(X , Y);
    		
    		String type = sc.next();
    		
    		String nextChar = sc.next();
    		next = new ArrayList<Node>();
    		while(nextChar.charAt(0) != '|') {
    			X = sc.nextInt();
    			Y = sc.nextInt();
    			next.add(this.nodes.get(new Position(X , Y)));
    			nextChar = sc.next();
    		}
    		if(type.equals("RED")) {
    			node = new RedNode(next , pos);
    		} else if(type.equals("YELLOW")) {
    			node = new YellowNode (next , pos);
    		} else {
    			node = new WhiteNode(next , pos);
    		}
    		nodes.put(pos , node);
    	}
    	sc.close();
    	this.start = node;
    	nodeEnd.setStart(node);
    	this.nodes.replace(nodeEnd.getPos(), nodeEnd);
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