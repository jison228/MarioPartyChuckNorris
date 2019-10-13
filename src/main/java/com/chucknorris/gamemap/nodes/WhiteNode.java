package com.chucknorris.gamemap.nodes;

import com.chucknorris.commons.Position;

import java.util.List;

public class WhiteNode extends Node {

    public WhiteNode(List<Node> next, Position pos) {
		super(next, pos);
	}

	@Override
	public String getType() {
		return "WHITE";
	}

}
