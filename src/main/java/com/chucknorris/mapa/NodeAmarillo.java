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
public class NodeAmarillo extends Node {

	

	public NodeAmarillo(ArrayList<Node> next, Position pos) {
		super(next, pos, "ROJO");
	}
	
	@Override
	public void applyRewards(Player p) {
		p.addCoins(10);
	}

}
