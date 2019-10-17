package com.chucknorris.gui;

import java.util.List;
import com.chucknorris.commons.Dice;
import com.chucknorris.gamemap.GameMap;
import com.chucknorris.player.Player;

public class GameInformation {
	public List<Player> players;
	public GameMap gameMap;
	public Dice dice;
	public double precioDolar;
	public double salarioInicial;
	public double pesosIniciales;
	public double dolaresIniciales;

	public GameInformation(List<Player> players, GameMap gameMap, 
			Dice dice, double precioDolar, double salarioInicial,
			double pesosIniciales, double dolaresIniciales) {
		this.players = players;
		this.gameMap = gameMap;
		this.dice = dice;
		this.precioDolar = precioDolar;
		this.salarioInicial = salarioInicial;
		this.pesosIniciales = pesosIniciales;
		this.dolaresIniciales = dolaresIniciales;
	}

}
