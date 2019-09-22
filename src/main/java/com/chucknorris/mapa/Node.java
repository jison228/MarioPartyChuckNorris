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
	private String type;


	public ArrayList<Node> nextNodes() {
		return next;
	}
	
	public Position getPos() {
		return pos;
	}
	

	public String getType() {
		return type;
	}
	
	public Node(ArrayList<Node> next, Position pos, String type) {
		this.next = next;
		this.pos = pos;
		this.type = type;
	}
	public abstract void applyRewards(Player p);
	
}
