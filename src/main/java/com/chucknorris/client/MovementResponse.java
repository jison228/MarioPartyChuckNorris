package com.chucknorris.client;

import java.util.List;
import java.util.Queue;

public class MovementResponse {
	public int diceResult;
	public ClientPlayer currentPlayer;
	public Queue<ClientNode> nodePath;
	public boolean bif;
	public List<ClientNode> options;
	public List<ClientPlayer> currentClientPlayerList;
	public boolean compra_dolares;

	public MovementResponse(int diceResult, ClientPlayer currentPlayer, Queue<ClientNode> nodePath, boolean bif,
			List<ClientNode> options, List<ClientPlayer> currentClientPlayerList, boolean compra_dolares) {
		this.diceResult = diceResult;
		this.currentPlayer = currentPlayer;
		this.nodePath = nodePath;
		this.bif = bif;
		this.options = options;
		this.currentClientPlayerList = currentClientPlayerList;
		this.compra_dolares = compra_dolares;
	}
}
