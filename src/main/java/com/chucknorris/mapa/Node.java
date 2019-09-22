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
	private ArrayList<Node> next;
	private Position pos;
	private String type;

	public abstract ArrayList<Node> nextNodes();
	public abstract Position getPos();
	public abstract String getType();
	
	public abstract void applyRewards(Player p);
	
}
