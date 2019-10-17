package com.chucknorris.gamemap.nodes;

import java.util.ArrayList;

import com.chucknorris.commons.Position;
import com.chucknorris.rewards.substractor.pesos.PesosPercentagePlayerSubstractor;

public class InvPesosMinusNode extends Node {
	public InvPesosMinusNode(ArrayList<Node> next,Position pos) {
		super(next,pos,new PesosPercentagePlayerSubstractor(10));
	}

	@Override
	public String getType() {
		return "IPM";
	}
}
