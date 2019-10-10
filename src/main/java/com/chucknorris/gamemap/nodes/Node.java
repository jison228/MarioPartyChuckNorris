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
	private RewardApplicable reward = new NoReward();
	private Position pos;//utilizado para la matriz de nodos unicamente

	public Node(List<Node> next, Position pos) {
		this.next = next;
		this.pos = pos;
	}

	public Node(List<Node> next, Position pos, RewardApplicable reward) {
		this(next, pos);

		this.reward = reward;
	}

	@Deprecated
	public List<Node> nextNodes() {
		return next;
	}

	@Deprecated
	public Position getPos() {
		return pos;
	}

	public abstract String getType();

	public void applyReward(Player playerExecutor, List<Player> players, GameContext context) {
		reward.apply(playerExecutor, players, context);
	}

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
