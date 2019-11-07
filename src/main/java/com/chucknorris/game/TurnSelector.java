package com.chucknorris.game;

import com.chucknorris.player.Player;

import java.util.List;

public class TurnSelector {
	private List<Player> playerList;
	private int index = 0;

	public TurnSelector(List<Player> playerList) {
		this.playerList = playerList;
	}

	Player getTurn() {
		int playerTurn = getIndex();

		Player player = playerList.get(playerTurn);

		index++;

		return player;
	}

	public Player getActualPlayerTurn() {
		resolveIndex();

		return playerList.get(index);
	}

	public boolean isPlayerTurn(Player player) {
		return player == getActualPlayerTurn();
	}

	private int getIndex() {
		resolveIndex();

		return index;
	}

	private void resolveIndex() {
		if (index >= playerList.size()) {
			index = 0;
		}
	}

	public void finishTurn() {
		getTurn();
	}
}
