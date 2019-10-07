package com.chucknorris.gamemap.presenter;

import com.chucknorris.commons.Position;
import com.chucknorris.gamemap.nodes.Node;

import java.util.List;

public interface NodePresenter {
	String present(String positionRepresentation, Node node, List<Position> next);
}
