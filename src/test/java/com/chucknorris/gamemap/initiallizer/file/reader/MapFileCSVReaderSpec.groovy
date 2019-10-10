package com.chucknorris.gamemap.initiallizer.file.reader

import com.chucknorris.commons.Position
import com.chucknorris.gamemap.GameMap
import com.chucknorris.gamemap.initiallizer.file.reader.csv.MapFileCSVReader
import com.chucknorris.gamemap.nodes.Node
import com.chucknorris.gamemap.presenter.NodeCSVPresenter
import com.chucknorris.gamemap.presenter.NodePresenter
import com.chucknorris.gamemap.presenter.PositionPresenter
import com.chucknorris.gamemap.presenter.WhiteSpacePositionPresenter
import org.junit.Assert
import org.junit.Test
import spock.lang.Specification

public class MapFileCSVReaderSpec extends Specification {

    private final Map<Position, String> expected_map_1_values = [
            new Position(0, 0): "0 0;RED;0 1",
            new Position(0, 1): "0 1;WHITE;0 3",
            new Position(0, 3): "0 3;WHITE;0 5",
            new Position(0, 5): "0 5;YELLOW;2 5",
            new Position(2, 5): "2 5;WHITE;3 5",
            new Position(3, 5): "3 5;WHITE;3 4",
            new Position(3, 4): "3 4;YELLOW;2 3 - 3 3",
            new Position(2, 3): "2 3;RED;2 2",
            new Position(2, 2): "2 2;WHITE;2 1",
            new Position(2, 1): "2 1;WHITE;3 0",
            new Position(3, 3): "3 3;WHITE;3 2",
            new Position(3, 2): "3 2;WHITE;3 1",
            new Position(3, 1): "3 1;WHITE;3 0",
            new Position(3, 0): "3 0;YELLOW;2 0",
            new Position(2, 0): "2 0;YELLOW;1 0",
            new Position(1, 0): "1 0;WHITE;0 0"
    ]

    @Test
    public void testMapReader_map_1() throws Exception {
        MapFileReadable mapFileReader = new MapFileCSVReader("map_1.txt");

        GameMap gameMap = mapFileReader.buildGameMap(new ArrayList<>());

        NodeCSVPresenter nodePresenter = new NodeCSVPresenter();

        WhiteSpacePositionPresenter positionPresenter = new WhiteSpacePositionPresenter();

        Assert.assertEquals(16, gameMap.nodesSize());

        for (Map.Entry<Position, String> entry : expected_map_1_values.entrySet()) {
            Node node = gameMap.getNode(entry.getKey());

            Assert.assertNotNull(node);

            assertNode(node, nodePresenter, positionPresenter, entry.getValue());
        }

        Node previusToFinalNode = gameMap.getNode(new Position(1, 0));
        Node finalNode = gameMap.getNode(new Position(0, 0));

        Assert.assertTrue(previusToFinalNode.isThereSameReferenceNextNode(finalNode));
    }

    private void assertNode(Node node, NodePresenter nodePresenter, PositionPresenter positionPresenter, String expected) {
        Assert.assertEquals(expected, node.present(nodePresenter, positionPresenter));
    }

}