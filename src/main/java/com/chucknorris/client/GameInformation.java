package com.chucknorris.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.chucknorris.commons.Position;
import com.chucknorris.gamemap.GameMap;
import com.chucknorris.gamemap.nodes.Node;
import com.chucknorris.player.Player;

public class GameInformation {
	private List<ClientPlayer> clientPlayersList;
	private List<ClientNode> clientNodesList;
	private double precioDolar;

	public GameInformation(List<Player> playersList, GameMap mapaNodes, double precioDolar) {
		this.clientPlayersList = new ArrayList<ClientPlayer>();
		ClientPlayer playerToList;
		for (int i = 0; i < playersList.size(); i++) {
			Player leer = playersList.get(i);
			playerToList = new ClientPlayer(leer.getCharacter(), leer.getCharacter(),
					leer.getNodeLocation().getPositionCoords(), leer.getPesos(), leer.getDolares(), leer.getSalario(),
					leer.getPowerupDescription());
			this.clientPlayersList.add(playerToList);
		}
		this.clientNodesList = new ArrayList<ClientNode>();
		ClientNode nodeToList;
		for (Map.Entry<Position, Node> entry : mapaNodes.getMap().entrySet()) {
			nodeToList = new ClientNode(entry.getValue().getType(), entry.getValue().getPositionCoords());
			this.clientNodesList.add(nodeToList);
		}
		this.precioDolar = precioDolar;
	}

	public List<ClientPlayer> getPlayers() {
		return clientPlayersList;
	}

	public List<ClientNode> getNodes() {
		return clientNodesList;
	}

	public double getPrecioDolar() {
		return precioDolar;
	}

}
