package com.chucknorris.gamemap.nodes;

import java.util.List;

import com.chucknorris.commons.Position;
import com.chucknorris.rewards.substractor.dolar.DolarPercentagePlayerSubstractor;

public class InvDolaresMinusNode extends Node {
	public InvDolaresMinusNode(List<Node> next, Position pos) {
		super(next, pos, new DolarPercentagePlayerSubstractor(5));
	}

	@Override
	public String getType() {
		return "IDM";
	}
}
