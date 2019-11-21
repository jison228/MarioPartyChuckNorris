package com.chucknorris.client;

public class ClientInfoSalas {
	public String name; 
	public int cantPlayers;
	public int cantSpectadores;
	public boolean playing;

	public ClientInfoSalas(String name,int cantPlayers, int cantSpectadores, boolean playing) {
		this.name = name;
		this.cantPlayers = cantPlayers;
		this.cantSpectadores = cantSpectadores - cantPlayers; //estas cosas no son entendibles pero bueno
		this.playing = playing;
	}	
}
