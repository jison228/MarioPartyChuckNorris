package com.chucknorris.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import com.chucknorris.commons.Position;
import com.chucknorris.gamemap.nodes.Node;
import com.chucknorris.player.Player;

public class MovementResponse {
	public int diceResult;
	public ClientPlayer currentPlayer;
	public Queue<Position> nodePath;
	public boolean bif;
	public List<ClientNode> options;
	public List<ClientPlayer> currentClientPlayerList;
	public boolean compra_dolares;

	public MovementResponse(int diceResult, Player currentPlayer, Queue<Position> nodePath, boolean bif,
			List<Node> options, List<Player> currentClientPlayerList, boolean compra_dolares) {
		this.diceResult = diceResult;
		this.currentPlayer = new ClientPlayer(currentPlayer);
		this.nodePath = nodePath;
		this.bif = bif;
		if(options!=null) {
			this.options = new ArrayList<ClientNode>();
			for(int i = 0; i < options.size(); i++) {
				this.options.add(new ClientNode(options.get(i)));
			}
		} else {
			this.options = null;
		}
		this.currentClientPlayerList = new ArrayList<ClientPlayer>();
		for(int i = 0; i < currentClientPlayerList.size(); i++) {
			this.currentClientPlayerList.add(new ClientPlayer(currentClientPlayerList.get(i)));
		}
		this.compra_dolares = compra_dolares;
	}
}