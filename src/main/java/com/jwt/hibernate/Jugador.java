package com.jwt.hibernate;

public class Jugador {

	private String username;
	private String password;
	private int wins;
	private int maxminigame;

	
	
	public Jugador(String username, String password, int wins, int maxminigame) {
		super();
		this.username = username;
		this.password = password;
		this.wins = wins;
		this.maxminigame = maxminigame;
	}

	public Jugador(String username, int wins, int maxminigame) {
		super();
		this.username = username;
		this.password = password;
		this.wins = wins;
		this.maxminigame = maxminigame;
	}
	
	public Jugador() {
		
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getMaxminigame() {
		return maxminigame;
	}

	public void setMaxminigame(int maxminigame) {
		this.maxminigame = maxminigame;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String pass) {
		this.password = pass;
	}

	@Override
	public String toString() {
		return "Jugador [username=" + username + ", password=" + password + ", wins=" + wins + ", maxminigame="
				+ maxminigame + "]";
	}

	

}
