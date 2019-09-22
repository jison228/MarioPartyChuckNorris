/**
 * 
 */
package com.chucknorris.mapa;

import java.util.ArrayList;

import com.chucknorris.commons.Position;
import com.chucknorris.player.Player;

/**
 * @author agufa
 *
 */
public class NodeEnd extends Node {

	

	public NodeEnd(Position pos) {
		super(null, pos, "END");
	}
	
	@Override
	public void applyRewards(Player p) {
		//por ahora nada
	}
	
	public void setStart(Node n) {
		this.next = new ArrayList<Node>();
		this.next.add(n);
	}

}
