package com.chucknorris.client;

import com.chucknorris.commons.Position;
import com.chucknorris.player.Player;

public class ClientPlayer {
	private String playerName;
	private String character;
	private Position position;
	private double pesos;
	private double dolares;
	private double salario;
	private String powerupDescription;

	public ClientPlayer(String playerName, String character, Position position, double pesos, double dolares, double salario, String powerupDescription) {
		this.playerName = playerName;
		this.character = character;
		this.position = position;
		this.pesos = pesos;
		this.dolares = dolares;
		this.salario = salario;
		this.powerupDescription = powerupDescription;
	}

	public ClientPlayer(Player player) {
		this.playerName = player.getCharacter();
		this.character = player.getCharacter();
		this.position = player.getNodeLocation().getPositionCoords();
		this.pesos = player.getPesos();
		this.dolares = player.getDolares();
		this.salario = player.getSalario();
		this.powerupDescription = player.getPowerupDescription();
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
