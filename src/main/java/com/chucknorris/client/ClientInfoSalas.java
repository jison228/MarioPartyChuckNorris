package com.chucknorris.client;

public class ClientInfoSalas {
	public int cantPlayers;
	public int cantSpectadores;
	public boolean playing;

	public ClientInfoSalas(int cantPlayers, int cantSpectadores, boolean playing) {
		this.cantPlayers = cantPlayers;
		this.cantSpectadores = cantSpectadores - cantPlayers;
		this.playing = playing;
	}	
}
