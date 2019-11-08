package com.chucknorris.gamemap;

import com.chucknorris.commons.Position;
import com.chucknorris.gamemap.nodes.Node;
import com.chucknorris.player.Player;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

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

	public int movePlayer(Player player, int leftMovements,Queue<Position> nodePath, List<Node> villereada) {
		Node node = nodes.get(player.getNodeLocation().getPositionCoords());
		nodePath.add(node.getPositionCoords());
		villereada.add(node);
		while (node.nextNodes().size() == 1 && leftMovements > 0) {
			node = node.nextNodes().get(0);
			nodePath.add(node.getPositionCoords());
			villereada.add(node);
			leftMovements--;
		}

		player.setNodeLocation(node);

		return leftMovements;
	}

	public int movePlayerFromIntersection(Player player, Node nextNode, int leftMovements,Queue<Position> nodePath, List<Node> villereada) {
		player.setNodeLocation(nextNode);

		return movePlayer(player, leftMovements - 1,nodePath,villereada);
	}

	public int movePlayerGUI(Player player, int leftMovements, Queue<Node> nodePath){
		Node node = nodes.get(player.getNodeLocation().getPositionCoords());
		nodePath.add(node);
		
		while (node.nextNodes().size() == 1 && leftMovements > 0) {
			node = node.nextNodes().get(0);
			leftMovements--;
			nodePath.add(node);
		}
		player.setNodeLocation(node);

		return leftMovements;
	}
	
	public int movePlayerFromIntersectionGUI(Player player,Node nextNode, int leftMovements, Queue<Node> nodePath){
		player.setNodeLocation(nextNode);
		nodePath.add(nextNode);
		return movePlayerGUI(player, leftMovements - 1, nodePath);
	}
	
	public int nodesSize() {
		return nodes.size();
	}
	
	public Node getNode(Position position) {
		return nodes.get(position);
	}
	
	public Map<Position, Node> getMap() {
		return nodes;
	}
}