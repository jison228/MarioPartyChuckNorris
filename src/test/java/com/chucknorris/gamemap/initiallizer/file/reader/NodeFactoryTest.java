package com.chucknorris.gamemap.initiallizer.file.reader;

import com.chucknorris.commons.Position;
import com.chucknorris.gamemap.nodes.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NodeFactoryTest {
	private static Map<String, Class> EXPECTED_MAP_TYPE_AND_CLASS;

	@BeforeClass
	public static void setup() {
		EXPECTED_MAP_TYPE_AND_CLASS = new HashMap<>();

		EXPECTED_MAP_TYPE_AND_CLASS.put("YELLOW", YellowNode.class);
		EXPECTED_MAP_TYPE_AND_CLASS.put("WHITE", WhiteNode.class);
		EXPECTED_MAP_TYPE_AND_CLASS.put("RED", RedNode.class);
		EXPECTED_MAP_TYPE_AND_CLASS.put("AFIPD", AfipDolarNode.class);
		EXPECTED_MAP_TYPE_AND_CLASS.put("AFIPP", AfipPesosNode.class);
	}

	@Test
	public void test_node_factory_specific_node() throws Exception {
		NodeFactory nodeFactory = new NodeFactory();

		for (Map.Entry<String, Class> entry : EXPECTED_MAP_TYPE_AND_CLASS.entrySet()) {
			Node node = nodeFactory.buildNode(new Position(0, 0), entry.getKey(), new ArrayList<>());
			Assert.assertEquals(entry.getValue().getName(), node.getClass().getName());
		}
	}

	@Test
	public void test_node_factory_uncataloged_node_expected_default_node() throws Exception {
		NodeFactory nodeFactory = new NodeFactory();

		Node node = nodeFactory.buildNode(new Position(0, 0), "SARAZA", new ArrayList<>());

		Assert.assertEquals(EXPECTED_MAP_TYPE_AND_CLASS.get("WHITE").getName(), node.getClass().getName());
	}

}