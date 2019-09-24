package com.chucknorris.mapa;

import java.util.ArrayList;
import com.chucknorris.commons.Position;
import com.chucknorris.player.Player;

public class NodeYellow extends Node {

	public NodeYellow(ArrayList<Node> next, Position pos) {
		super(next, pos);
	}
	
	@Override
	public void applyRewards(Player p) {
		p.addCoins(10);
	}

	@Override
	public String getType() {
		return "YELLOW";
	}

}
