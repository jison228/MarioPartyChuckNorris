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
public abstract class Node {
	protected ArrayList<Node> next;
	private Position pos;

	public ArrayList<Node> nextNodes() {
		return next;
	}
	
	public Position getPos() {
		return pos;
	}
	


	public Node(ArrayList<Node> next, Position pos) {
		this.next = next;
		this.pos = pos;
	}
	public abstract void applyRewards(Player p);
	public abstract String getType();
	
}
