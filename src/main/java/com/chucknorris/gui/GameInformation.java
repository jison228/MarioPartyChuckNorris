package com.chucknorris.gui;

import java.util.List;
import com.chucknorris.commons.Dice;
import com.chucknorris.gamemap.GameMap;
import com.chucknorris.player.Player;

public class GameInformation {
	public GameMap gameMap;
	public List<Player> players;
	public double salarioInicial;
	public Dice dice;
	public double precioDolar;
	public GameInformation( List<Player> players,GameMap gameMap, double salarioInicial, Dice dice,
			double precioDolar) {
		this.players = players;
		this.gameMap = gameMap;
		this.salarioInicial = salarioInicial;
		this.dice = dice;
		this.precioDolar = precioDolar;
	}
	

}
