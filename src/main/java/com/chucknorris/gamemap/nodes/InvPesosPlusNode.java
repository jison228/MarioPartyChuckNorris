package com.chucknorris.gamemap.nodes;

import java.util.ArrayList;

import com.chucknorris.commons.Position;
import com.chucknorris.rewards.PercentagePesosPlayerAdder;


public class InvPesosPlusNode extends Node{
	public InvPesosPlusNode(ArrayList<Node> next,Position pos) {
		super(next,pos,new PercentagePesosPlayerAdder(20));
	}

	@Override
	public String getType() {
		return "IPP";
	}
}
