package com.chucknorris.gamemap;

import com.chucknorris.commons.Position;
import com.chucknorris.player.Player;

import java.util.ArrayList;
import java.util.Map;

public class GameMap {
	private Map<Position, Node> nodes;
	private Node start;

	public GameMap(Map<Position, Node> nodesRead, Node firstNode) {
		this.nodes = nodesRead;
		start = firstNode;
	}

	public void initializePlayers(ArrayList<Player> players) {
		for (Player p : players) {
			p.setPos(start);
		}
	}

	public int movePlayer(Player p, int movs) {
		Node node = nodes.get(p.getPos().getPos());
		int leftMovs = movs;
		while (node.nextNodes().size() == 1 && leftMovs > 0) {
			node = node.nextNodes().get(0);
			leftMovs--;
		}
		p.setPos(node);

		if (leftMovs == 0) {
			node.applyRewards(p);
		}

		return leftMovs;
	}

	public int movePlayer(Player p, int movs, Node pos) {
		p.setPos(pos);
		return movePlayer(p, movs - 1);
	}

	public int nodesSize() {
		return nodes.size();
	}

	public Node getNode(Position position) {
		return nodes.get(position);
	}
}