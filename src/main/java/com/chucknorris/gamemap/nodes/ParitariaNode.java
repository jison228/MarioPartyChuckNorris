package com.chucknorris.gamemap.nodes;

import java.util.List;

import com.chucknorris.commons.Position;
import com.chucknorris.rewards.PercentageSalarioPlayerAdder;

public class ParitariaNode extends Node {

	public ParitariaNode(List<Node> next, Position positionCoords) {
		super(next, positionCoords, new PercentageSalarioPlayerAdder(20));
	}

	@Override
	public String getType() {
		return "PARI";
	}

}
