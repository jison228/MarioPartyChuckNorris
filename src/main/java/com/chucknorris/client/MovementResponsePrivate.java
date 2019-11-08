package com.chucknorris.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import com.chucknorris.commons.Position;
import com.chucknorris.gamemap.nodes.Node;
import com.chucknorris.player.Player;

public class MovementResponsePrivate {
	public int diceResult;
	public String playerID;
	public Queue<Position> nodePath;
	public boolean bif;
	public List<ClientNode> options;
	public List<ClientPlayer> currentClientPlayerList;
	public boolean compra_dolares;

	public MovementResponsePrivate(int diceResult, String playerID, Queue<Position> nodePath,
			List<Player> currentClientPlayerList, List<Node> options, boolean bif, boolean compra_dolares) {
		this.diceResult = diceResult;
		this.playerID = playerID;
		this.nodePath = nodePath;
		this.bif = bif;
		if (options != null) {
			this.options = new ArrayList<ClientNode>();
			for (int i = 0; i < options.size(); i++) {
				this.options.add(new ClientNode(options.get(i)));
			}
		} else {
			this.options = null;
		}
		this.currentClientPlayerList = new ArrayList<ClientPlayer>();
		for (int i = 0; i < currentClientPlayerList.size(); i++) {
			this.currentClientPlayerList.add(new ClientPlayer(currentClientPlayerList.get(i)));
		}
		this.compra_dolares = compra_dolares;
	}
}