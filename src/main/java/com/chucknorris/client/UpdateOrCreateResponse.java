package com.chucknorris.client;

import java.util.ArrayList;
import java.util.List;

import com.chucknorris.User;
import com.chucknorris.server.Sala;

public class UpdateOrCreateResponse {
	public List<User> usuarios;
	public List<ClientInfoSalas> salas;

	public UpdateOrCreateResponse(List<User> usuarios, List<Sala> salas) {
		this.usuarios = usuarios;
		this.salas = new ArrayList<ClientInfoSalas>();

		for (int i = 0; i < salas.size(); i++) {
			this.salas.add(new ClientInfoSalas(salas.get(i).players.size(), salas.get(i).threadsMap.size(), salas.get(i).playing));
		}
	}

}
