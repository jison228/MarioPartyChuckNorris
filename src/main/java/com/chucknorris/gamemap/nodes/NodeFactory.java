package com.chucknorris.gamemap.nodes;

import com.chucknorris.commons.Position;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public final class NodeFactory {
	private static final Class<WhiteNode> DEFAULT_NODE_VALUE = WhiteNode.class;
	private Map<String, Class> CATALOGED_NODES;

	public NodeFactory() {
		CATALOGED_NODES = new TreeMap<>();

		CATALOGED_NODES.put("YELLOW", YellowNode.class);
		CATALOGED_NODES.put("RED", RedNode.class);
		CATALOGED_NODES.put("WHITE", WhiteNode.class);
		CATALOGED_NODES.put("IPP", InvPesosPlusNode.class);
		CATALOGED_NODES.put("IPM", InvPesosMinusNode.class);
	}

	@SuppressWarnings("unchecked")
	public Node buildNode(Position position, String type, List<Node> nextNodes) throws Exception {
		Class nodeClass = CATALOGED_NODES.getOrDefault(type, DEFAULT_NODE_VALUE);

		Constructor constructor = nodeClass.getConstructor(ensureClass(nextNodes), ensureClass(position));

		return (Node) constructor.newInstance(nextNodes, position);
	}

	private Class ensureClass(Object arg) {
		return arg.getClass();
	}
}
