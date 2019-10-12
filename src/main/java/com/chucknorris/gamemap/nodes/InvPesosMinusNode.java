package com.chucknorris.gamemap.nodes;

import java.util.ArrayList;

import com.chucknorris.commons.Position;
import com.chucknorris.rewards.pesos.PesosReward;

public class InvPesosMinusNode extends Node {
	public InvPesosMinusNode(ArrayList<Node> next,Position pos) {
		super(next,pos,new PesosReward(0.90));
	}

	@Override
	public String getType() {
		return "IPM";
	}
}
