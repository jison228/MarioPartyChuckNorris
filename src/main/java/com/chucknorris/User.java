package com.chucknorris;

public class User {
	private String playerID;
	private int partidasGanadas;
	private int maximoPuntaje;
	
	public User(String playerID, int partidasGanadas, int maximoPuntaje) {
		this.playerID = playerID;
		this.partidasGanadas = partidasGanadas;
		this.maximoPuntaje = maximoPuntaje;
	}

	public String getPlayerID() {
		return playerID;
	}
	
	public int getPartidasGanadas() {
		return partidasGanadas;
	}
	
	public int getMaximoPuntaje() {
		return maximoPuntaje;
	}
}
