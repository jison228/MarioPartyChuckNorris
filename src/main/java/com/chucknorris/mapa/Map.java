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
	
	
	public Response mover(Player p, int movs) {
		Response res;
		Node node = nodes.get(p.getPos());
		int leftMovs = movs;
		while(node.nextNodes().size() == 1 && leftMovs > 0) {
			node = node.nextNodes().get(0);
		}
		p.setPos(node.getPos().getX(), node.getPos().getX());

		ArrayList<Position> availablePositions = new ArrayList<Position>();
		
		if(leftMovs>0) {
			for(Node pos : node.nextNodes()) {
				availablePositions.add(pos.getPos());
			}
		}
		
		if(movs == 0) {
			node.applyRewards(p);
		}
		
		return new Response(node.getPos(),node.getType(),availablePositions,leftMovs);
	}
}
