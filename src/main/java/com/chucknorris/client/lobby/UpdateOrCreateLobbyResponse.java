package com.chucknorris.client.lobby;

import java.util.List;

import com.chucknorris.User;
import com.chucknorris.client.ClientLobbySala;

public class UpdateOrCreateLobbyResponse {
	public List<User> usuarios;
	public List<ClientLobbySala> salas;

	public UpdateOrCreateLobbyResponse(List<User> usuarios, List<ClientLobbySala> salas) {
		this.usuarios = usuarios;
		this.salas = salas;
	}

}
