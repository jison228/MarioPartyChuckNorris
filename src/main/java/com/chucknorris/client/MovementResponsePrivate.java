package com.chucknorris.client;

import java.util.List;
import java.util.Queue;

import com.chucknorris.commons.Position;

public class MovementResponsePrivate {
	public int diceResult;
	public String playerID;
	public Queue<Position> nodePath;
	public List<ClientNode> options;
	public List<ClientPlayer> currentClientPlayerList;
	public boolean compra_dolares;
	public int movementsLeft;

	public MovementResponsePrivate(int diceResult, String playerID, Queue<Position> nodePath,
			List<ClientPlayer> currentClientPlayerList, List<ClientNode> options, boolean compra_dolares,
			int movementsLeft) {
		this.diceResult = diceResult;
		this.playerID = playerID;
		this.nodePath = nodePath;
		this.options = options;
		this.currentClientPlayerList = currentClientPlayerList;
		this.compra_dolares = compra_dolares;
		this.movementsLeft = movementsLeft;
	}
}