package com.chucknorris.game;

import java.util.Queue;

import com.chucknorris.commons.Position;

public class GameResponse {
	public int diceResult;
	public Queue<Position> nodePath;
	public String playerId;
	public boolean compraDolares;
	public int movementsLeft;
	
	public GameResponse(int diceResult, Queue<Position> nodePath, String playerId, boolean compraDolares,int movementsLeft) {
		this.diceResult = diceResult;
		this.nodePath = nodePath;
		this.playerId = playerId;
		this.compraDolares = compraDolares;
		this.movementsLeft = movementsLeft;
	}
	
}
