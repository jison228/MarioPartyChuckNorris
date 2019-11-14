package com.chucknorris.gamemap.nodes;

import com.chucknorris.commons.Position;
import com.chucknorris.gamemap.presenter.NodePresenter;
import com.chucknorris.gamemap.presenter.PositionPresenter;
import com.chucknorris.player.Player;
import com.chucknorris.rewards.GameContext;
import com.chucknorris.rewards.NoReward;
import com.chucknorris.rewards.RewardApplicable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class Node {
	protected List<Node> next;
	private RewardApplicable reward;
	private Position positionCoords;// utilizado para la matriz de nodos unicamente

	public Node(List<Node> next, Position positionCoords) {
		this.next = next;
		this.positionCoords = positionCoords;
		this.reward = new NoReward();
	}

	public Node(List<Node> next, Position positionCoords, RewardApplicable reward) {
		this.next = next;
		this.positionCoords = positionCoords;
		this.reward = reward;
	}

	public List<Node> nextNodes() {
		return next;
	}

	public Position getPositionCoords() {
		return positionCoords;
	}

	public abstract String getType();

	public void applyReward(Player playerExecutor, List<Player> players, GameContext context) {
		reward.apply(playerExecutor, players, context);
	}

	public String present(NodePresenter presenter, PositionPresenter positionPresenter) {
		List<Position> nextPositions = next.stream().map(it -> it.positionCoords).collect(Collectors.toList());

		return presenter.present(positionCoords.present(positionPresenter), this, nextPositions);
	}

	public void replaceWithThisNodeIfNextHasOneWithSamePosition(Node firstNode) {
		Optional<Node> nodeToReplace = next.stream()
				.filter(node -> node.positionCoords.equals(firstNode.positionCoords)).findFirst();

		nodeToReplace.ifPresent(node -> {
			next.remove(node);
			next.add(firstNode);
		});
	}

	public boolean isThereSameReferenceNextNode(Node finalNode) {
		return next.stream().anyMatch(nextNode -> nextNode == finalNode);
	}
}
