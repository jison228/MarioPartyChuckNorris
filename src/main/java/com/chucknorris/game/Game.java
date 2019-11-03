package com.chucknorris.game;

import com.chucknorris.commons.Dice;
import com.chucknorris.commons.Position;
import com.chucknorris.gamemap.GameMap;
import com.chucknorris.gamemap.nodes.Node;
import com.chucknorris.gui.GameInformation;
import com.chucknorris.player.Player;
import com.chucknorris.server.command.response.BadRequestResponse;
import com.chucknorris.server.command.response.GameResponse;
import com.chucknorris.server.command.response.ServerResponse;

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
	private TurnSelector turnSelector;
	private int movementLeftFromIntersection = 0;
	//ArrayList de minijuegos


	Game(List<Player> players, GameMap gameMap, Dice dice, double precioDolar, String id, TurnSelector turnSelector) {
		this.players = players;
		this.gameMap = gameMap;
		this.dice = dice;
		this.precioDolar = precioDolar;
		this.id = id;
		this.turnSelector = turnSelector;
	}

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
		this.id = info.id;
	}

	public Game(List<Player> players, GameMap gameMap, Dice dice) {
		this(players, gameMap);
		this.dice = dice;
	}

	public ServerResponse play(Player player) {
		if (!turnSelector.isPlayerTurn(player)) {
			return new BadRequestResponse();
		}

		int diceResult = dice.roll();

		Queue<Position> positionQueue = new LinkedList<>();

		int movementsLeft = gameMap.movePlayer(player, diceResult, positionQueue);

		applyRewardIfApplies(player, movementsLeft);

		changeTurnIfApplies(movementsLeft);

		saveIntersectionDataIfApplies(player, movementsLeft);

		GameResponse resultado = buildGameResponse();
		resultado.movementsLeft = movementsLeft;
		resultado.diceResult = diceResult;
		resultado.positionPathQueue = positionQueue;

		fillNextNodeIfInIntersection(resultado, player, movementsLeft);

		return resultado;
	}

	private void fillNextNodeIfInIntersection(GameResponse resultado, Player player, int movementsLeft) {
		if (movementsLeft > 0) {
			resultado.nextNodesIntersection = player.getNodeLocation().nextPositions();
		}
	}

	private void saveIntersectionDataIfApplies(Player player, int movementsLeft) {
		movementLeftFromIntersection = Math.max(movementsLeft, 0);
	}

	private void changeTurnIfApplies(int movementsLeft) {
		if (movementsLeft == 0) {
			turnSelector.finishTurn();
		}
	}

	public ServerResponse resolveIntersection(Player player, Position nextPosition) {
		Node nextNode = gameMap.getNode(nextPosition);

		if (nextNode == null || !turnSelector.isPlayerTurn(player) || !isNextPositionWithinNextNodes(player, nextNode)) {
			return new BadRequestResponse();
		}

		int movementsLeft = movementLeftFromIntersection;

		Queue<Position> positionQueue = new LinkedList<>();

		movementsLeft = gameMap.movePlayerFromIntersection(player, nextNode, movementsLeft, positionQueue);

		applyRewardIfApplies(player, movementsLeft);

		changeTurnIfApplies(movementsLeft);

		saveIntersectionDataIfApplies(player, movementsLeft);

		GameResponse resultado = buildGameResponse();
		resultado.movementsLeft = movementsLeft;
		resultado.positionPathQueue = positionQueue;

		return resultado;
	}

	private boolean isNextPositionWithinNextNodes(Player player, Node nextNode) {
		return player.getNodeLocation().hasThisPositionWithinNext(nextNode);
	}

	private GameResponse buildGameResponse() {
		GameResponse gameResponse = new GameResponse();
		gameResponse.gameId = id;

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

	public List<Player> getPlayerList() {
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
