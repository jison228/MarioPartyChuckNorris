package com.chucknorris.client;

import com.chucknorris.commons.Position;

public class clientPlayer {
	private String playerName;
	private String character;
	private Position position;
	private double pesos;
	private double dolares;
	private double salario;
	private String powerupDescription;

	public clientPlayer(String playerName, String character, Position position, double pesos, double dolares, double salario, String powerupDescription) {
		this.playerName = playerName;
		this.character = character;
		this.position = position;
		this.pesos = pesos;
		this.dolares = dolares;
		this.salario = salario;
		this.powerupDescription = powerupDescription;
	}

	public void updateInformation(Position position, double pesos, double dolares, double salario) {
		this.position = position;
		this.pesos = pesos;
		this.dolares = dolares;
		this.salario = salario;
	}

	public String getCharacter() {
		return character;
	}

	public Position getPosition() {
		return position;
	}

	public double getPesos() {
		return pesos;
	}

	public double getDolares() {
		return dolares;
	}

	public double getSalario() {
		return salario;
	}
	
	public String getPowerupDescription() {
		return powerupDescription;
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public void setPosition(Position position) {
		this.position = position;
	}
}
