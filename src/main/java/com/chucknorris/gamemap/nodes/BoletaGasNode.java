package com.chucknorris.gamemap.nodes;

import java.util.List;

import com.chucknorris.commons.Position;
import com.chucknorris.rewards.FixedPesosPlayerSubstractor;

public class BoletaGasNode extends Node{

	public BoletaGasNode(List<Node> next, Position positionCoords) {
		super(next, positionCoords, new FixedPesosPlayerSubstractor(50));
	}

	@Override
	public String getType() {
		return "BG";
	}
}
