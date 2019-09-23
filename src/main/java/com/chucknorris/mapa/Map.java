/**
 * 
 */
package com.chucknorris.mapa;

import java.util.ArrayList;
import java.util.HashMap;

import com.chucknorris.commons.Position;
import com.chucknorris.player.Player;

/**
 * @author agufa
 *
 */
public class Map {
	HashMap<Position,Node> nodes;
	Node start;
	
	public void initializePlayers(ArrayList<Player> players) {
		for(Player p: players) {
			p.setPos(start.getPos().getX(), start.getPos().getY());
		}
	}
	
	//Pendiente de modificar cuando se lea el mapa mediante un .txt
	public Map(ArrayList<Node> nodes, Node start) {
		this.nodes = new HashMap<Position,Node>();
		for (Node node : nodes) {
			this.nodes.put(node.getPos(), node);
		}
		this.start = start;
	}




	public Response mover(Player p, int movs) {
		Node node = nodes.get(p.getPos());
		int leftMovs = movs;
		while(node.nextNodes().size() == 1 && leftMovs > 0) {
			node = node.nextNodes().get(0);
			leftMovs--;
		}
		p.setPos(node.getPos().getX(), node.getPos().getY());

		ArrayList<Position> availablePositions = new ArrayList<Position>();
		
		if(leftMovs>0) {
			for(Node pos : node.nextNodes()) {
				availablePositions.add(pos.getPos());
			}
		}else {
			node.applyRewards(p);
		}
		
		return new Response(node.getPos(),node.getType(),availablePositions,leftMovs);
	}
	
	public Response mover(Player p, int movs, Position pos) {
		p.setPos(pos.getX(), pos.getY());
		return mover(p,movs-1);
	}
	
	
}