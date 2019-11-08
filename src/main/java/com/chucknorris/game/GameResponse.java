package com.chucknorris.game;

import java.util.Queue;

import com.chucknorris.gamemap.nodes.Node;

public class GameResponse {
	public int movementsLeft;
	public int diceResult;
	public Queue<Node> nodePath;
	public String gameId;
	//public boolean salida;
}
