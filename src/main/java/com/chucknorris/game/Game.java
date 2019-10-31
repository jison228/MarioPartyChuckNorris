package com.chucknorris.game;

import com.chucknorris.commons.Dice;
import com.chucknorris.gamemap.GameMap;
import com.chucknorris.gamemap.nodes.Node;
import com.chucknorris.gui.GameInformation;
import com.chucknorris.player.Player;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

public class Game {
	private List<Player> players;
	private int turn;
	private GameMap gameMap;
	private Dice dice;
	private double precioDolar;
	private String id;
	//ArrayList de minijuegos

	public Game(List<Player> players, GameMap gameMap) {
		this.players = players;
		this.turn = 0;
		this.gameMap = gameMap;
		this.dice = new Dice(1, 6);
	}

	public Game(GameInformation info) {
		this.players = info.players;
		this.gameMap = info.gameMap;
		this.dice = info.dice;
		this.precioDolar = info.precioDolar;
	}

	public Game(List<Player> players, GameMap gameMap, Dice dice) {
		this(players, gameMap);
		this.dice = dice;
	}

	public GameResponse play(Player player) {
		int diceResult = dice.roll();

		Queue<Node> nodePath = new LinkedList<>();

		int movementsLeft = gameMap.movePlayer(player, diceResult, nodePath);

		applyRewardIfApplies(player, movementsLeft);

		return buildGameResponse(movementsLeft);
	}

	public GameResponse playGUI(Player player) {
		int diceResult = dice.roll();

		Queue<Node> nodePath = new LinkedList<>();

		int movementsLeft = gameMap.movePlayer(player, diceResult, nodePath);

		applyRewardIfApplies(player, movementsLeft);

		GameResponse resultado = new GameResponse();
		resultado.movementsLeft = movementsLeft;
		resultado.diceResult = diceResult;
		resultado.nodePath = nodePath;

		return resultado;
	}

	public GameResponse resolveIntersection(Player player, Node nextNode, int movementsLeft) {
		movementsLeft = gameMap.movePlayerFromIntersection(player, nextNode, movementsLeft, new LinkedList<>());

		applyRewardIfApplies(player, movementsLeft);

		return buildGameResponse(movementsLeft);
	}

	public GameResponse resolveIntersectionGUI(Player player, Node nextNode, int movementsLeft) {
		Queue<Node> nodePath = new LinkedList<Node>();

		movementsLeft = gameMap.movePlayerFromIntersection(player, nextNode, movementsLeft, nodePath);

		applyRewardIfApplies(player, movementsLeft);

		GameResponse resultado = new GameResponse();
		resultado.movementsLeft = movementsLeft;
		resultado.nodePath = nodePath;

		return resultado;
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

	public int getCurrentTurn() {
		return turn;
	}

	public GameMap getGameMap() {
		return gameMap;
	}

	public List<Player> getPlayerList(){
		return players;
	}

	public void endTurn() {
		turn++;
	}

	public void aumentarPrecioDolar() {
		precioDolar++;
	}

	public Double getPrecioDolar() {
		return precioDolar;
	}

	public Player getPlayer(String playerId) {
		Optional<Player> playerFound = players.stream()
				.filter(player -> player.returnIfSameId(playerId) != null)
				.findFirst();

		return playerFound.orElse(null);
	}

	public String printId() {
		return id;
	}
}
