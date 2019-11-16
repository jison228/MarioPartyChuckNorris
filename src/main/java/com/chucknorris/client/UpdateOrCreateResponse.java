package com.chucknorris.client;

import java.util.List;

import com.chucknorris.User;

public class UpdateOrCreateResponse {
	public List<User> usuarios;
	public List<ClientInfoSalas> salas;

	public UpdateOrCreateResponse(List<User> usuarios, List<ClientInfoSalas> salas) {
		this.usuarios = usuarios;
		this.salas = salas;
	}

}
