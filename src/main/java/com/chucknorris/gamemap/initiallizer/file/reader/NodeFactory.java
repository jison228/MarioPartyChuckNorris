package com.chucknorris.gamemap.initiallizer.file.reader;

import com.chucknorris.commons.Position;
import com.chucknorris.gamemap.Node;
import com.chucknorris.gamemap.RedNode;
import com.chucknorris.gamemap.WhiteNode;
import com.chucknorris.gamemap.YellowNode;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class NodeFactory {
	public static final Class<WhiteNode> DEFAULT_NODE_VALUE = WhiteNode.class;
	private Map<String, Class> CATALOGED_NODES;

	public NodeFactory() {
		CATALOGED_NODES = new TreeMap<>();

		CATALOGED_NODES.put("YELLOW", YellowNode.class);
		CATALOGED_NODES.put("RED", RedNode.class);
		CATALOGED_NODES.put("WHITE", WhiteNode.class);
	}

	public Node buildNode(Position position, String type, List<Node> nextNodes) throws Exception {
		Class nodeClass = CATALOGED_NODES.getOrDefault(type, DEFAULT_NODE_VALUE);

		Constructor<Node> constructor = nodeClass.getConstructor(this.ensureClass(nextNodes), this.ensureClass(position));

		return constructor.newInstance(nextNodes, position);
	}

	private Class<?> ensureClass(Object arg) {
		return arg.getClass();
	}
}
