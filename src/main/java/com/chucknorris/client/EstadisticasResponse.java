package com.chucknorris.client;

import java.util.List;

import com.jwt.hibernate.Jugador;
import com.jwt.hibernate.Partida;



public class EstadisticasResponse {
	public Jugador player;
	public List<Partida> partidas;

	public EstadisticasResponse(Jugador player, List<Partida> partidas) {
		this.player = player;
		this.partidas = partidas;
	}
	
}
