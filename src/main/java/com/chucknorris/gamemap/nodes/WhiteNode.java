package com.chucknorris.gamemap.nodes;

import com.chucknorris.commons.Position;

import java.util.ArrayList;

public class WhiteNode extends Node {

	public WhiteNode(ArrayList<Node> next, Position pos) {
		super(next, pos);
	}

	@Override
	public String getType() {
		return "WHITE";
	}

}
