package com.chucknorris.gamemap.nodes;

import java.util.List;

import com.chucknorris.commons.Position;
import com.chucknorris.rewards.Powerup;

public class PowerupNode extends Node {

	public PowerupNode(List<Node> next, Position positionCoords) {
		super(next, positionCoords, new Powerup());
	}

	@Override
	public String getType() {
		return "PWR";
	}

}
