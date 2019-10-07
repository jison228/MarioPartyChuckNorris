package com.chucknorris.gamemap.nodes;

import com.chucknorris.commons.Position;
import com.chucknorris.gamemap.presenter.NodePresenter;
import com.chucknorris.gamemap.presenter.PositionPresenter;
import com.chucknorris.player.Player;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class Node {
	protected List<Node> next;
	private Position pos;//utilizado para la matriz de nodos unicamente

	public Node(List<Node> next, Position pos) {
		this.next = next;
		this.pos = pos;
	}

	@Deprecated
	public List<Node> nextNodes() {
		return next;
	}

	@Deprecated
	public Position getPos() {
		return pos;
	}

	public abstract void applyRewards(Player p);

	public abstract String getType();

	public String present(NodePresenter presenter, PositionPresenter positionPresenter) {
		List<Position> nextPositions = next.stream()
				.map(it -> it.pos)
				.collect(Collectors.toList());

		return presenter.present(pos.present(positionPresenter), this, nextPositions);
	}

	public void replaceWithThisNodeIfNextHasOneWithSamePosition(Node firstNode) {
		Optional<Node> nodeToReplace = next.stream().filter(node -> node.pos.equals(firstNode.pos)).findFirst();

		nodeToReplace.ifPresent(node -> {
			next.remove(node);
			next.add(firstNode);
		});
	}

	public boolean isThereSameReferenceNextNode(Node finalNode) {
		return next.stream().anyMatch(nextNode -> nextNode == finalNode);
	}
}
