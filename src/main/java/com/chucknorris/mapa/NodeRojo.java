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
public class NodeRojo extends Node {

	

	public NodeRojo(ArrayList<Node> next, Position pos) {
		super(next, pos);
	}
	
	@Override
	public void applyRewards(Player p) {
		p.addCoins(3);
	}

	@Override
	public String getType() {
		return "RED";
	}

}
