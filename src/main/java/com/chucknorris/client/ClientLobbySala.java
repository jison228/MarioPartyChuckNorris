package com.chucknorris.client;

public class ClientLobbySala {
	public String name; 
	public int cantPlayers;
	public int cantSpectadores;
	public boolean playing;
	public boolean priv;

	public ClientLobbySala(String name,int cantPlayers, int cantSpectadores, boolean playing, boolean priv) {
		this.name = name;
		this.cantPlayers = cantPlayers;
		this.cantSpectadores = cantSpectadores - cantPlayers; //estas cosas no son entendibles pero bueno
		this.playing = playing;
		this.priv = priv;
	}	
}
