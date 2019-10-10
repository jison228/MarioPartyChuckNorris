package com.chucknorris.gamemap;

import com.chucknorris.commons.Position;
import com.chucknorris.gamemap.nodes.Node;
import com.chucknorris.player.Player;

import java.util.List;
import java.util.Map;

public class GameMap {
	private Map<Position, Node> nodes;
	private Node start;

	public GameMap(Map<Position, Node> nodesRead, Node firstNode) {
		this.nodes = nodesRead;
		start = firstNode;
	}

	public void initializePlayers(List<Player> players) {
		for (Player p : players) {
			p.setNodeLocation(start);
		}
	}

	public int movePlayer(Player p, int movs) {
		Node node = nodes.get(p.getNodeLocation().getPositionCoords());
		int leftMovs = movs;
		while (node.nextNodes().size() == 1 && leftMovs > 0) {
			node = node.nextNodes().get(0);
			leftMovs--;
		}
		p.setNodeLocation(node);

		if (leftMovs == 0) {
			//TODO usar la nueva firma
			node.applyReward(p, null, null);
		}

		return leftMovs;
	}

	public int movePlayer(Player p, int movs, Node pos) {
		p.setNodeLocation(pos);
		return movePlayer(p, movs - 1);
	}

	public MapResponse movePlayers(Player p, int leftMovements) {
		Node node = nodes.get(p.getNodeLocation().getPositionCoords());

		while (node.nextNodes().size() == 1 && leftMovements > 0) {
			node = node.nextNodes().get(0);
			leftMovements--;
		}
		p.setNodeLocation(node);

		return buildResponse(leftMovements, node);
	}

	private MapResponse buildResponse(int leftMovements, Node node) {
		MapResponse mapResponse = new MapResponse();

		mapResponse.endMovementNode = node;

		mapResponse.movementsLeft = leftMovements;

		return mapResponse;
	}

	public int nodesSize() {
		return nodes.size();
	}

	public Node getNode(Position position) {
		return nodes.get(position);
	}
}