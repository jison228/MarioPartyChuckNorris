package com.chucknorris.game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.chucknorris.commons.Dice;
import com.chucknorris.commons.Position;
import com.chucknorris.gamemap.GameMap;
import com.chucknorris.gamemap.initiallizer.file.reader.csv.MapFileCSVReader;
import com.chucknorris.gamemap.nodes.Node;
import com.chucknorris.gui.GameInformation;
import com.chucknorris.player.Player;

public class Game {
	private List<Player> players;
	private int turn;
	private GameMap gameMap;
	private Dice dice;
	private double precioDolar;
	// ArrayList de minijuegos

	public Game(List<Player> players, GameMap gameMap, double precioDolar) {
		this.players = players;
		this.turn = 0;
		this.gameMap = gameMap;
		this.dice = new Dice(1, 6);
		this.precioDolar = precioDolar;
	}

	public Game(List<Player> players, String nombreMapa, int diceMin, int diceMax, double precioDolar) throws Exception {
		this.players = players;
		this.turn = 0;
		MapFileCSVReader mapFileCSVReader = new MapFileCSVReader( nombreMapa + ".txt");
		this.gameMap = mapFileCSVReader.buildGameMap();
		this.gameMap.initializePlayers(this.players);
		this.dice = new Dice(diceMin, diceMax);
		this.precioDolar = precioDolar;
	}
	
	public GameResponseGUI playGUI(Player player) {
		int diceResult = dice.roll();
		
		Queue<Node> nodePath = new LinkedList<Node>();
		
		int movementsLeft = gameMap.movePlayerGUI(player, diceResult, nodePath);
		
		applyRewardIfApplies(player, movementsLeft);
		
		GameResponseGUI resultado = new GameResponseGUI();
		resultado.movementsLeft = movementsLeft;
		resultado.diceResult = diceResult;
		resultado.nodePath = nodePath;
		
		return resultado;
	}
	
	public GameResponseGUI resolveIntersectionGUI(Player player, Node nextNode, int movementsLeft) {
		Queue<Node> nodePath = new LinkedList<Node>();
		
		movementsLeft = gameMap.movePlayerFromIntersectionGUI(player, nextNode, movementsLeft, nodePath);
		
		applyRewardIfApplies(player, movementsLeft);
		
		GameResponseGUI resultado = new GameResponseGUI();
		resultado.movementsLeft = movementsLeft;
		resultado.nodePath = nodePath;
		
		return resultado;
	}
	
	public Game(GameInformation info) {
		this.players = info.players;
		this.gameMap = info.gameMap;
		this.dice = info.dice;
		this.precioDolar = info.precioDolar;
	}

	public Game(List<Player> players, GameMap gameMap, Dice dice) {
		this(players, gameMap, 20);
		this.dice = dice;
	}

	public GameResponse play(Player player) {
		int diceResult = dice.roll();
		String playerID = player.getPlayerID();
		Queue<Position> nodePath = new LinkedList<Position>();
		List<Node> villereada = new ArrayList<Node>();

		int movementsLeft = gameMap.movePlayer(player, diceResult, nodePath, villereada);

		boolean comprarDolares = validarCompraDolares(villereada);
		if (comprarDolares) {
			player.cobrarSalario();
		}
		applyRewardIfApplies(player, movementsLeft);

		return new GameResponse(diceResult, nodePath, playerID, comprarDolares, movementsLeft);
	}

	public GameResponse resolveIntersection(Player player, Node nextNode, int movementsLeft) {
		String playerID = player.getPlayerID();
		Queue<Position> nodePath = new LinkedList<Position>();
		List<Node> villereada = new ArrayList<Node>();

		movementsLeft = gameMap.movePlayerFromIntersection(player, nextNode, movementsLeft, nodePath, villereada);

		boolean comprarDolares = validarCompraDolares(villereada);
		if (comprarDolares) {
			player.cobrarSalario();
		}
		applyRewardIfApplies(player, movementsLeft);

		return new GameResponse(1, nodePath, playerID, comprarDolares, movementsLeft);
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

	private boolean validarCompraDolares(List<Node> villereada) {
		boolean dolares = false;
		for (int i = 1; i < villereada.size(); i++) {
			Node recorrer = villereada.get(i);
			if (recorrer.getType().equals("END")) {
				dolares = true;
			}
		}
		return dolares;
	}
}
