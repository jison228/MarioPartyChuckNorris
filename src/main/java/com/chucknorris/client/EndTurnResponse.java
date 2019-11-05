package com.chucknorris.client;

public class EndTurnResponse {
	public int currentTurn;
	public double currentPrecioDolar;
	public ClientPlayer currentPlayer;

	public EndTurnResponse(int currentTurn, double currentPrecioDolar, ClientPlayer currentPlayer) {
		this.currentTurn = currentTurn;
		this.currentPrecioDolar = currentPrecioDolar;
		this.currentPlayer = currentPlayer;
	}

}
