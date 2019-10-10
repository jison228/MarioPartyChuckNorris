package com.chucknorris.gamemap.nodes;

import com.chucknorris.commons.Position;

import java.util.Collections;

public class EndNode extends Node {

	public EndNode(Position pos) {
		super(Collections.emptyList(), pos);
	}

	@Override
	public String getType() {
		return "END";
	}

}
