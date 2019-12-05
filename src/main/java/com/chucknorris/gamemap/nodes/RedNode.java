package com.chucknorris.gamemap.nodes;

import java.util.List;

import com.chucknorris.commons.Position;
import com.chucknorris.rewards.coins.CoinsReward;

public class RedNode extends Node {

	public RedNode(List<Node> next, Position pos) {
		super(next, pos, new CoinsReward(3));
	}

	@Override
	public String getType() {
		return "RED";
	}

}
