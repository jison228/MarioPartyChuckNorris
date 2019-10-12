package com.chucknorris.gamemap.nodes;

import java.util.ArrayList;

import com.chucknorris.commons.Position;
import com.chucknorris.rewards.pesos.PesosReward;

public class InvPesosPlusNode extends Node{
	public InvPesosPlusNode(ArrayList<Node> next,Position pos) {
		super(next,pos,new PesosReward(1.20));
	}

	@Override
	public String getType() {
		return "IPP";
	}
}
