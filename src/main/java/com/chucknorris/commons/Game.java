package com.chucknorris.commons;

import com.chucknorris.mapa.Map;
import com.chucknorris.player.Player;

import java.util.ArrayList;

public class Game {
    private ArrayList<Player> players;
    private int turn;
    private Map map;
    private Dice dice;
    //ArrayList de minijuegos

    public Game(ArrayList<Player> players, Map map) {
        this.players = players;
        this.turn = 1;
        this.map = map;
        this.dice = new Dice(1, 6);
    }

    public void endTurn() {
        //verificar si alguien gano (por ahora no hay corte)
        turn++;
    }

    public int roll() {
        return dice.roll();
    }

    public ArrayList<Player> getPlayerList() {
        return players;
    }

    public Map getMap() {
        return map;
    }

    public int getCurrentTurn() {
        return turn;
    }
}
