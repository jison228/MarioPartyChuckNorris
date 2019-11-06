package com.chucknorris.client;

import com.chucknorris.player.Player;

public class EndTurnResponse {
	public int currentTurn;
	public double currentPrecioDolar;
	public ClientPlayer currentPlayer;

	public EndTurnResponse(int currentTurn, double currentPrecioDolar, Player currentPlayer) {
		ClientPlayer playerToClientPlayer = new ClientPlayer(currentPlayer);
		this.currentTurn = currentTurn;
		this.currentPrecioDolar = currentPrecioDolar;
		this.currentPlayer = playerToClientPlayer;
	}

}
