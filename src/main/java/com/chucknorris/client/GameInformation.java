package com.chucknorris.client;

import java.util.List;

import com.chucknorris.commons.Dice;

public class GameInformation {
	private List<clientPlayer> players;
	private List<clientNode> nodes;
	private double precioDolar;
	private int turn;

	public GameInformation(List<clientPlayer> players, List<clientNode> nodes, Dice dice, double precioDolar) {
		this.players = players;
		this.nodes = nodes;
		this.precioDolar = precioDolar;
		this.turn = 0;
	}

	public List<clientPlayer> getPlayers() {
		return players;
	}

	public List<clientNode> getNodes() {
		return nodes;
	}
	
	public double getPrecioDolar() {
		return precioDolar;
	}
	
	public int getTurn() {
		return turn;
	}
	
	
}
