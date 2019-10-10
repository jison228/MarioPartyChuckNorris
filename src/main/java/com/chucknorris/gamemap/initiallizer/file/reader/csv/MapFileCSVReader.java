package com.chucknorris.gamemap.initiallizer.file.reader.csv;

import com.chucknorris.commons.Position;
import com.chucknorris.gamemap.GameMap;
import com.chucknorris.gamemap.initiallizer.file.reader.LineDataDto;
import com.chucknorris.gamemap.initiallizer.file.reader.MapFileReadable;
import com.chucknorris.gamemap.initiallizer.file.reader.position.PositionReadable;
import com.chucknorris.gamemap.initiallizer.file.reader.position.PositionReader;
import com.chucknorris.gamemap.nodes.EndNode;
import com.chucknorris.gamemap.nodes.Node;
import com.chucknorris.gamemap.nodes.NodeFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MapFileCSVReader implements MapFileReadable {

	private static final int POSITION_FIELD = 0;
	private static final int NODE_TYPE_FIELD = 1;
	private static final int NEXT_NODES_POSITION_FIELD = 2;

	private static final String FIELD_SEPARATOR = ";";
	private static final String NEXT_NODES_SEPARATOR = "-";

	private final NodeFactory nodeFactory = new NodeFactory();

	private final PositionReadable positionReader = new PositionReader();

	private final Map<Position, Node> nodesRead = new HashMap<>();

	private final List<LineDataDto> nodeLinesRead = new LinkedList<>();

	private final String fileName;

	public MapFileCSVReader(String fileName) {
		this.fileName = fileName;
	}

	public GameMap buildGameMap() throws Exception {
		readFileAndLoadFlatNodes();

		Node firstNode = buildNodes();

		return new GameMap(nodesRead, firstNode);
	}

	private void readFileAndLoadFlatNodes() throws IOException {
		File mapFile = new File("maps/" + fileName);

		BufferedReader bufferedReader = new BufferedReader(new FileReader(mapFile));

		bufferedReader.readLine();

		String line = bufferedReader.readLine();

		while (line != null) {
			String[] lineData = line.split(FIELD_SEPARATOR);

			String nodeType = lineData[NODE_TYPE_FIELD];

			Position position = positionReader.readPosition(lineData[POSITION_FIELD]);

			LineDataDto lineDataDto = new LineDataDto(position, nodeType, lineData[NEXT_NODES_POSITION_FIELD]);

			nodeLinesRead.add(lineDataDto);

			line = bufferedReader.readLine();
		}

		bufferedReader.close();
	}

	private Node buildNodes() throws Exception {

		Node firstNode = null;

		for (LineDataDto lineDataDto : nodeLinesRead) {
			Position position = lineDataDto.position;

			if (nodesRead.isEmpty()) {
				nodesRead.put(position, new EndNode(position));
			}

			List<Node> nextNodes = getNextNodes(lineDataDto.nextNodes);

			Node node = nodeFactory.buildNode(position, lineDataDto.nodeType, nextNodes);

			if (firstNode == null) {
				firstNode = node;
			}
			nodesRead.put(position, node);
		}

		fixFirstNode(nodesRead, firstNode);

		return firstNode;
	}

	private void fixFirstNode(Map<Position, Node> nodesRead, Node firstNode) {
		nodesRead.values()
				.forEach(node -> node.replaceWithThisNodeIfNextHasOneWithSamePosition(firstNode));
	}

	private List<Node> getNextNodes(String lineData) throws Exception {
		String[] nextNodesPositions = lineData.split(NEXT_NODES_SEPARATOR);

		List<Node> nextNodes = new ArrayList<>();
		for (String nextNode : nextNodesPositions) {
			Position nextNodePosition = positionReader.readPosition(nextNode);

			Node resultNode = nodesRead.get(nextNodePosition);

			if (resultNode == null) {
				resultNode = buildNode(nextNodePosition);
			}

			nextNodes.add(resultNode);
		}

		return nextNodes;
	}

	private Node buildNode(Position nextNodePosition) throws Exception {
		LineDataDto lineDataDto = nodeLinesRead.stream()
				.filter(dto -> dto.position.equals(nextNodePosition))
				.findFirst()
				.get();

		List<Node> nextNodes = getNextNodes(lineDataDto.nextNodes);

		Node node = nodeFactory.buildNode(nextNodePosition, lineDataDto.nodeType, nextNodes);

		nodesRead.put(nextNodePosition, node);

		return node;
	}
}
