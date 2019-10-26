package com.chucknorris.game;

import com.chucknorris.gamemap.nodes.Node;
import com.chucknorris.server.command.response.ServerResponse;

import java.util.Queue;

public class GameResponse implements ServerResponse {
	public int movementsLeft;
	public int diceResult;
	public Queue<Node> nodePath;
	public String gameId;
	//public boolean salida;
}
