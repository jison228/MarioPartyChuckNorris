package com.chucknorris.game;

import com.chucknorris.commons.Dice;
import com.chucknorris.gamemap.GameMap;
import com.chucknorris.gamemap.nodes.Node;
import com.chucknorris.player.Player;

import java.util.List;

public class Game {
	private List<Player> players;
	private GameMap gameMap;
	private Dice dice;
	private GameContext gameContextContext;
	//ArrayList de minijuegos

	public Game(List<Player> players, GameMap gameMap) {
		this.players = players;
		this.gameMap = gameMap;
		this.dice = new Dice(1, 6);
		//crear game context
	}

	public Game(List<Player> players, GameMap gameMap, Dice dice) {
		this(players, gameMap);
		this.dice = dice;
	}

	public GameResponse play(Player player) {
		int diceResult = dice.roll();

		int movementsLeft = gameMap.movePlayer(player, diceResult);

		applyRewardIfApplies(player, movementsLeft);

		return buildGameResponse(movementsLeft);
	}

	public GameResponse resolveIntersection(Player player, Node nextNode, int movementsLeft) {
		movementsLeft = gameMap.movePlayerFromIntersection(player, nextNode, movementsLeft);

		applyRewardIfApplies(player, movementsLeft);

		return buildGameResponse(movementsLeft);
	}

	private GameResponse buildGameResponse(int movementsLeft) {
		GameResponse gameResponse = new GameResponse();

		gameResponse.movementsLeft = movementsLeft;

		return gameResponse;
	}

	private void applyRewardIfApplies(Player player, int movementsLeft) {
		if (movementsLeft == 0) {
			player.applyReward(players, null);
		}
	}

}
