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
public class NodeWhite extends Node {

	

	public NodeWhite(ArrayList<Node> next, Position pos) {
		super(next, pos, "WHITE");
	}
	
	@Override
	public void applyRewards(Player p) {
		//no hace nada
	}

}
