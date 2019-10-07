package com.chucknorris.gamemap.nodes;

import com.chucknorris.commons.Position;
import com.chucknorris.player.Player;

public class EndNode extends Node {

	public EndNode(Position pos) {
		super(null, pos);
	}

	@Override
	public void applyRewards(Player p) {
		//por ahora nada
	}

	@Override
	public String getType() {
		return "END";
	}

}
