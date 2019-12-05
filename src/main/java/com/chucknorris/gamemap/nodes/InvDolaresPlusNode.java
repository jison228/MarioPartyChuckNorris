package com.chucknorris.gamemap.nodes;

import java.util.List;

import com.chucknorris.commons.Position;
import com.chucknorris.rewards.PercentageDolaresPlayerAdder;

public class InvDolaresPlusNode extends Node {
	public InvDolaresPlusNode(List<Node> next, Position pos) {
		super(next, pos, new PercentageDolaresPlayerAdder(10));
	}

	@Override
	public String getType() {
		return "IDP";
	}

}
