package com.chucknorris.commons;

import com.chucknorris.gamemap.GameMap;
import com.chucknorris.player.Player;

import java.util.List;

public class Game {
	private List<Player> players;
    private int turn;
    private GameMap gameMap;
    private Dice dice;
    //ArrayList de minijuegos

	public Game(List<Player> players, GameMap gameMap) {
        this.players = players;
        this.turn = 1;
        this.gameMap = gameMap;
        this.dice = new Dice(1, 6);
    }

    public void endTurn() {
        //verificar si alguien gano (por ahora no hay corte)
        turn++;
    }

    public int roll() {
        return dice.roll();
    }

	public List<Player> getPlayerList() {
        return players;
    }

    public GameMap getMap() {
        return gameMap;
    }

    public int getCurrentTurn() {
        return turn;
    }
}
