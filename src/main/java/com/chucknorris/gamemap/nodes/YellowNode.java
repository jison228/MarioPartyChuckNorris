package com.chucknorris.gamemap.nodes;

import com.chucknorris.commons.Position;
import com.chucknorris.rewards.coins.CoinsReward;

import java.util.ArrayList;

public class YellowNode extends Node {

	public YellowNode(ArrayList<Node> next, Position pos) {
		super(next, pos, new CoinsReward(10));
	}

	@Override
	public String getType() {
		return "YELLOW";
	}

}
