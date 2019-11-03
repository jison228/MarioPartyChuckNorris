package com.chucknorris.client;

public class ServerResponse3 {
	public int currentTurn;
	public double currentPrecioDolar;
	public clientPlayer currentPlayer;

	public ServerResponse3(int currentTurn, double currentPrecioDolar, clientPlayer currentPlayer) {
		this.currentTurn = currentTurn;
		this.currentPrecioDolar = currentPrecioDolar;
		this.currentPlayer = currentPlayer;
	}

}
