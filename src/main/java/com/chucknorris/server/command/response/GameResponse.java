package com.chucknorris.server.command.response;

import com.chucknorris.gamemap.nodes.Node;

import java.util.Queue;

public class GameResponse extends ServerResponse {
	public int movementsLeft;
	public int diceResult;
	public Queue<Node> nodePath;
	public String gameId;
	//public boolean salida;
}
