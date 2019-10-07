package com.chucknorris.gamemap;

import com.chucknorris.commons.Position;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class NodeFactory {
	private static final Class<WhiteNode> DEFAULT_NODE_VALUE = WhiteNode.class;
	private Map<String, Class> CATALOGED_NODES;

	public NodeFactory() {
		CATALOGED_NODES = new TreeMap<>();

		CATALOGED_NODES.put("YELLOW", YellowNode.class);
		CATALOGED_NODES.put("RED", RedNode.class);
		CATALOGED_NODES.put("WHITE", WhiteNode.class);
	}

	@SuppressWarnings("unchecked")
	public Node buildNode(Position position, String type, List<Node> nextNodes) throws Exception {
		Class nodeClass = CATALOGED_NODES.getOrDefault(type, DEFAULT_NODE_VALUE);

		Constructor constructor = nodeClass.getConstructor(this.ensureClass(nextNodes), this.ensureClass(position));

		return (Node) constructor.newInstance(nextNodes, position);
	}

	private Class<?> ensureClass(Object arg) {
		return arg.getClass();
	}
}
