package com.chucknorris.gamemap.presenter;

import com.chucknorris.commons.Position;
import com.chucknorris.gamemap.nodes.Node;

import java.util.Iterator;
import java.util.List;

public class NodeCSVPresenter implements NodePresenter {

	@Override
	public String present(String positionRepresentation, Node node, List<Position> next) {
		PositionPresenter positionPresenter = new WhiteSpacePositionPresenter();

		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(String.format("%s;%s;", positionRepresentation, node.getType()));

		Iterator<Position> nextPosition = next.iterator();

		while (nextPosition.hasNext()) {

			Position position = nextPosition.next();

			stringBuilder.append(position.present(positionPresenter));

			if (nextPosition.hasNext()) {
				stringBuilder.append(" - ");
			}
		}

		return stringBuilder.toString();
	}

}
