package com.chucknorris.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import com.chucknorris.commons.Position;

public class MovementResponsePublic {
	public int diceResult;
	public String playerID;
	public Queue<Position> nodePath;
	public List<ClientPlayer> currentClientPlayerList;

	public MovementResponsePublic(int diceResult, String playerID, Queue<Position> nodePath,
			List<ClientPlayer> currentClientPlayerList) {
		this.diceResult = diceResult;
		this.playerID = playerID;
		this.nodePath = nodePath;
		this.currentClientPlayerList = new ArrayList<ClientPlayer>();
		this.currentClientPlayerList = currentClientPlayerList;

	}
}