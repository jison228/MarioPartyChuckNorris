package com.chucknorris.gamemap.initiallizer.file.reader;

import com.chucknorris.commons.Position;
import com.chucknorris.gamemap.GameMap;
import com.chucknorris.gamemap.Node;
import com.chucknorris.gamemap.presenter.NodeCSVPresenter;
import com.chucknorris.gamemap.presenter.NodePresenter;
import com.chucknorris.gamemap.presenter.PositionPresenter;
import com.chucknorris.gamemap.presenter.WhiteSpacePositionPresenter;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MapFileReaderTest {
	private final Map<Position, String> expected_map_1_values = new HashMap<>();

	{
		expected_map_1_values.put(new Position(0, 0), "0 0;RED;0 1");
		expected_map_1_values.put(new Position(0, 1), "0 1;WHITE;0 3");
		expected_map_1_values.put(new Position(0, 3), "0 3;WHITE;0 5");
		expected_map_1_values.put(new Position(0, 5), "0 5;YELLOW;2 5");
		expected_map_1_values.put(new Position(2, 5), "2 5;WHITE;3 5");
		expected_map_1_values.put(new Position(3, 5), "3 5;WHITE;3 4");
		expected_map_1_values.put(new Position(3, 4), "3 4;YELLOW;2 3 - 3 3");
		expected_map_1_values.put(new Position(2, 3), "2 3;RED;2 2");
		expected_map_1_values.put(new Position(2, 2), "2 2;WHITE;2 1");
		expected_map_1_values.put(new Position(2, 1), "2 1;WHITE;3 0");
		expected_map_1_values.put(new Position(3, 3), "3 3;WHITE;3 2");
		expected_map_1_values.put(new Position(3, 2), "3 2;WHITE;3 1");
		expected_map_1_values.put(new Position(3, 1), "3 1;WHITE;3 0");
		expected_map_1_values.put(new Position(3, 0), "3 0;YELLOW;2 0");
		expected_map_1_values.put(new Position(2, 0), "2 0;YELLOW;1 0");
		expected_map_1_values.put(new Position(1, 0), "1 0;WHITE;0 0");
	}

	@Test
	public void testMapReader_map_1() throws Exception {
		MapFileReadable mapFileReader = new MapFileReader("mapa1.txt");

		GameMap gameMap = mapFileReader.buildGameMap();

		NodeCSVPresenter nodePresenter = new NodeCSVPresenter();

		WhiteSpacePositionPresenter positionPresenter = new WhiteSpacePositionPresenter();

		Assert.assertEquals(16, gameMap.nodesSize());

		for (Map.Entry<Position, String> entry : expected_map_1_values.entrySet()) {
			Node node = gameMap.getNode(entry.getKey());

			Assert.assertNotNull(node);

			assertNode(node, nodePresenter, positionPresenter, entry.getValue());
		}

	}

	private void assertNode(Node node, NodePresenter nodePresenter, PositionPresenter positionPresenter, String expected) {
		Assert.assertEquals(expected, node.present(nodePresenter, positionPresenter));
	}

}