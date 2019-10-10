package com.chucknorris.gamemap.nodes;

import com.chucknorris.commons.Position;
import com.chucknorris.rewards.coins.CoinsReward;

import java.util.ArrayList;

public class RedNode extends Node {

	public RedNode(ArrayList<Node> next, Position pos) {
		super(next, pos, new CoinsReward(3));
	}

	@Override
	public String getType() {
		return "RED";
	}

}
