package com.chucknorris.client;

import java.util.List;

import com.chucknorris.User;

public class UpdateOrCreateResponse {
	public List<User> usuarios;
	public List<ClientLobbySala> salas;

	public UpdateOrCreateResponse(List<User> usuarios, List<ClientLobbySala> salas) {
		this.usuarios = usuarios;
		this.salas = salas;
	}

}
