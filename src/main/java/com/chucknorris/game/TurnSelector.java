package com.chucknorris.game;

import com.chucknorris.player.Player;

import java.util.List;

public class TurnSelector {
	private List<Player> playerList;
	private int index = 0;

	public TurnSelector(List<Player> playerList) {
		this.playerList = playerList;
	}

	public Player getTurn() {
		int playerTurn = getIndex();

		return playerList.get(playerTurn);
	}

	private int getIndex() {
		if (index >= playerList.size()) {
			index = 0;
		}

		return index;
	}
}
