package com.chucknorris.game;

import com.chucknorris.commons.Dice;
import com.chucknorris.gamemap.GameMap;
import com.chucknorris.player.Player;

import java.util.List;

public class GameBuilder {
	private List<Player> players;
	private GameMap gameMap;
	private Dice dice;
	private double dollarPrice;
	private String id;
	private TurnSelector turnSelector;

	public GameBuilder setPlayers(List<Player> players) {
		this.players = players;
		return this;
	}

	public GameBuilder setGameMap(GameMap gameMap) {
		this.gameMap = gameMap;
		return this;
	}

	public GameBuilder setDice(Dice dice) {
		this.dice = dice;
		return this;
	}

	public GameBuilder setDollarPrice(double dollarPrice) {
		this.dollarPrice = dollarPrice;
		return this;
	}

	public GameBuilder setId(String id) {
		this.id = id;
		return this;
	}

	public GameBuilder setTurnSelector(TurnSelector turnSelector) {
		this.turnSelector = turnSelector;
		return this;
	}

	public Game build() {
		return new Game(players, gameMap, dice, dollarPrice, id, turnSelector);
	}
}