package com.chucknorris.mapa;

import java.util.ArrayList;
import java.util.HashMap;
import com.chucknorris.commons.Position;
import com.chucknorris.player.Player;

public class Map {
	private HashMap<Position,Node> nodes;
	private Node start;
	
	public void initializePlayers(ArrayList<Player> players) {
		for(Player p: players) {
			p.setPos(start);
		}
	}
	
	//Pendiente de modificar cuando se lea el mapa mediante un .txt
	public Map(ArrayList<Node> nodes, Node start) {
		this.nodes = new HashMap<Position,Node>();
		for (Node node : nodes) {
			this.nodes.put( node.getPos() , node);
		}
		this.start = start;
	}

	public Response movePlayer(Player p, int movs) {
		Node node = nodes.get( p.getPos().getPos() );
		int leftMovs = movs;
		while( node.nextNodes().size() == 1 && leftMovs > 0) {
			node = node.nextNodes().get(0);
			leftMovs--;
		}
		p.setPos(node);

		ArrayList<Position> availablePositions = new ArrayList<Position>();
		
		if(leftMovs>0) {
			for(Node pos : node.nextNodes()) {
				availablePositions.add( pos.getPos() );
			}
		}else {
			node.applyRewards(p);
			availablePositions = null;
		}
		
		return new Response( node , availablePositions , leftMovs );
	}
	
	public Response movePlayer(Player p, int movs, Node pos) {
		p.setPos(pos);
		return movePlayer( p , movs-1 );
	}
	
	
}