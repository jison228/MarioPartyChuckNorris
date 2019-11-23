package com.chucknorris.client.sala;

import java.util.List;

import com.chucknorris.User;

public class UpdateOrCreateSalaResponse {
	public List<User> players;
	public List<User> spectators;
	
	public UpdateOrCreateSalaResponse(List<User> players, List<User> spectators) {
		this.players = players;
		this.spectators = spectators;
	}
}
