package com.chucknorris.gamemap.nodes;

import java.util.List;

import com.chucknorris.commons.Position;

public class WhiteNode extends Node {

	public WhiteNode(List<Node> next, Position pos) {
		super(next, pos);
	}

	@Override
	public String getType() {
		return "WHITE";
	}

}
