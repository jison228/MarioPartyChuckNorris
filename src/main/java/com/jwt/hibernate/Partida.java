package com.jwt.hibernate;

public class Partida {

	private int id;
	private String winner;
	private String player1;
	private String player2;
	private String player3;
	private String player4;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWinner() {
		return winner;
	}
	public void setWinner(String winner) {
		this.winner = winner;
	}
	public String getPlayer1() {
		return player1;
	}
	public void setPlayer1(String player1) {
		this.player1 = player1;
	}
	public String getPlayer2() {
		return player2;
	}
	public void setPlayer2(String player2) {
		this.player2 = player2;
	}
	public String getPlayer3() {
		return player3;
	}
	public void setPlayer3(String player3) {
		this.player3 = player3;
	}
	public String getPlayer4() {
		return player4;
	}
	public void setPlayer4(String player4) {
		this.player4 = player4;
	}
	@Override
	public String toString() {
		return "Partida [id=" + id + ", winner=" + winner + ", player1=" + player1 + ", player2=" + player2
				+ ", player3=" + player3 + ", player4=" + player4 + "]";
	}

}
