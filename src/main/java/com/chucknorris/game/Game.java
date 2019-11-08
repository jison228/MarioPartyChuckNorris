package com.chucknorris.game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.chucknorris.commons.Dice;
import com.chucknorris.commons.Position;
import com.chucknorris.gamemap.GameMap;
import com.chucknorris.gamemap.nodes.Node;
import com.chucknorris.gui.GameInformation;
import com.chucknorris.player.Player;

public class Game {
	private List<Player> players;
	private int turn;
	private GameMap gameMap;
	private Dice dice;
	private double precioDolar;
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
		String playerID = player.getPlayerID();
		Queue<Position> nodePath = new LinkedList<Position>();
		List<Node> villereada = new ArrayList<Node>();
		
		int movementsLeft = gameMap.movePlayer(player, diceResult,nodePath,villereada);
		
		boolean comprarDolares = validarCompraDolares(villereada);
		applyRewardIfApplies(player, movementsLeft);
		
		
		return new GameResponse(diceResult, nodePath, playerID, comprarDolares);
	}
	
	public GameResponse resolveIntersection(Player player, Node nextNode, int movementsLeft) {
		String playerID = player.getPlayerID();
		Queue<Position> nodePath = new LinkedList<Position>();
		List<Node> villereada = new ArrayList<Node>();
		
		movementsLeft = gameMap.movePlayerFromIntersection(player, nextNode, movementsLeft,nodePath,villereada);
		
		boolean comprarDolares = validarCompraDolares(villereada);
		applyRewardIfApplies(player, movementsLeft);

		return new GameResponse(0, nodePath, playerID, comprarDolares);
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
	
	private boolean validarCompraDolares(List<Node> villereada) {
		boolean dolares = false;
		for(int i=1;i<villereada.size();i++) {
			Node recorrer = villereada.get(i);
			if(recorrer.getType().equals("END")) {
				dolares = true;
			}
		}
		return dolares;
	}
}
