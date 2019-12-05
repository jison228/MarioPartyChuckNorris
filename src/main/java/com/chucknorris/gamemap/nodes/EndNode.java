package com.chucknorris.gamemap.nodes;

import java.util.List;

import com.chucknorris.commons.Position;

public class EndNode extends Node {

	public EndNode(List<Node> next, Position pos) {
		super(next, pos);
	}

	@Override
	public String getType() {
		return "END";
	}

}
