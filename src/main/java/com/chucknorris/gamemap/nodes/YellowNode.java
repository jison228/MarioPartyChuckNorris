package com.chucknorris.gamemap.nodes;

import com.chucknorris.commons.Position;
import com.chucknorris.rewards.coins.CoinsReward;

import java.util.List;

public class YellowNode extends Node {

    public YellowNode(List<Node> next, Position pos) {
		super(next, pos, new CoinsReward(10));
	}

	@Override
	public String getType() {
		return "YELLOW";
	}

}
