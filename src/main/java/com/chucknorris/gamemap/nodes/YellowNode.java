package com.chucknorris.gamemap.nodes;

import java.util.List;

import com.chucknorris.commons.Position;
import com.chucknorris.rewards.coins.CoinsReward;

public class YellowNode extends Node {

	public YellowNode(List<Node> next, Position pos) {
		super(next, pos, new CoinsReward(10));
	}

	@Override
	public String getType() {
		return "YELLOW";
	}

}
