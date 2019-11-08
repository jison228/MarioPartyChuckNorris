package com.chucknorris.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import com.chucknorris.commons.Position;
import com.chucknorris.player.Player;

public class MovementResponsePublic {
	public int diceResult;
	public String playerID;
	public Queue<Position> nodePath;
	public List<ClientPlayer> currentClientPlayerList;

	public MovementResponsePublic(int diceResult, String playerID, Queue<Position> nodePath,
			List<Player> currentClientPlayerList) {
		this.diceResult = diceResult;
		this.playerID = playerID;
		this.nodePath = nodePath;
		this.currentClientPlayerList = new ArrayList<ClientPlayer>();
		for (int i = 0; i < currentClientPlayerList.size(); i++) {
			this.currentClientPlayerList.add(new ClientPlayer(currentClientPlayerList.get(i)));
		}

	}
}