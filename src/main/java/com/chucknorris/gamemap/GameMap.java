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

	@Deprecated
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

	@Deprecated
	public int movePlayer(Player p, int movs, Node pos) {
		p.setNodeLocation(pos);
		return movePlayer(p, movs - 1);
	}

	public int movePlayers(Player player, int leftMovements) {
		Node node = nodes.get(player.getNodeLocation().getPositionCoords());

		while (node.nextNodes().size() == 1 && leftMovements > 0) {
			node = node.nextNodes().get(0);
			leftMovements--;
		}

		player.setNodeLocation(node);

		return leftMovements;
	}

	public int movePlayerFromIntersection(Player player, Node nextNode, int leftMovements) {
		player.setNodeLocation(nextNode);

		return movePlayers(player, leftMovements - 1);
	}

	public int nodesSize() {
		return nodes.size();
	}

	public Node getNode(Position position) {
		return nodes.get(position);
	}
}