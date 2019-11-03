package com.chucknorris.client;

import java.util.List;
import java.util.Queue;

public class ServerResponse1 {
	public int diceResult;
	public clientPlayer currentPlayer;
	public Queue<clientNode> nodePath;
	public boolean bif;
	public List<clientNode> options;

	public ServerResponse1(int diceResult, clientPlayer currentPlayer, Queue<clientNode> nodePath, boolean bif,
			List<clientNode> options) {
		this.diceResult = diceResult;
		this.currentPlayer = currentPlayer;
		this.nodePath = nodePath;
		this.bif = bif;
		this.options = options;
	}
}
