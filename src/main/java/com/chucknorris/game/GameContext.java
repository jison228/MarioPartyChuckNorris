package com.chucknorris.game;

import java.util.List;

import com.chucknorris.player.Player;

public class GameContext {
	
	private int turn;
	private double usdValue;
	private double incrementConstant;
	private double basicSalary;
	private double argentinaConstant = 0.9;
	
	public GameContext(double usdValue,double incrementConstant, double basicSalary) {
		this.turn = 0;
		this.usdValue = usdValue;
		this.incrementConstant = incrementConstant;
		this.basicSalary = basicSalary;
	}
	
	public void nextTurn() {
		this.turn++;
		this.incrementConstant *= (1+0.1*(int)(turn/5));
		this.usdValue *= this.incrementConstant;
		this.basicSalary *= (this.incrementConstant * this.argentinaConstant);
	}

	public void buyUSD(Player player, double cant, List<Player> players) {
		player.payPesos(cant);
		player.giveUSD(cant/usdValue);
		this.usdValue *= 1+(cant/totalBalance(players)[0]);
		
	}	

	public void sellUSD(Player player, double cant, List<Player> players) {
		player.payUSD(cant);
		player.givePesos(cant*usdValue);
		this.usdValue *= 1-(cant/totalBalance(players)[1]);
	}	
	
	public double[] totalBalance(List<Player> players) {
		double dollars = 0, pesos = 0;
		for(Player player: players) {
			dollars += player.getUSD();
			pesos += player.getPesos();
		}
		return new double[] {dollars, pesos};
	}

	
}
