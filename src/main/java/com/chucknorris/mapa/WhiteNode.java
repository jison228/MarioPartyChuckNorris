package com.chucknorris.mapa;

import java.util.ArrayList;
import com.chucknorris.commons.Position;
import com.chucknorris.player.Player;

public class WhiteNode extends Node {

	public WhiteNode(ArrayList<Node> next, Position pos) {
		super(next, pos);
	}
	
	@Override
	public void applyRewards(Player p) {
		//no hace nada
	}

	@Override
	public String getType() {
		return "WHITE";
	}

}
