package com.chucknorris.client.sala;

import java.util.List;

import com.chucknorris.User;

public class ClientRealSala {
	public String name;
	public List<User> players;
	public List<User> spectators;

	public ClientRealSala(String name, List<User> players, List<User> spectators) {
		this.name = name;
		this.players = players;
		this.spectators = spectators;
	}

}
