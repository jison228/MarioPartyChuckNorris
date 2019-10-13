package com.chucknorris.node.test;

import com.chucknorris.commons.Position;
import com.chucknorris.gamemap.nodes.*;
import com.chucknorris.player.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class NodeTest {

	Player p, p2;

	@Before
	public void executedBeforeEach() {
		p = new Player("Milei", 0);
		p2 = new Player("Cristi", 100, 150);
	}

	@Test
	public void nodeConstructorTest() {
		Node node1 = new YellowNode(null, new Position(3, 3));
		Node node2 = new RedNode(null, new Position(3, 4));
		ArrayList<Node> lista = new ArrayList<Node>();
		lista.add(node1);
		lista.add(node2);
		Node nodeTest = new RedNode(lista, new Position(4, 4));

		assertEquals(nodeTest.getType(), "RED");
		assertEquals(nodeTest.nextNodes(), lista);
	}

	@Test
	public void redNodeARTest() {
		Node nodeTest = new RedNode(null, new Position(4, 4));

		nodeTest.applyReward(p, null, null);
		assertEquals(p.getCoins(), 3);
	}

	@Test
	public void yellowNodeARTest() {
		Node nodeTest = new YellowNode(null, new Position(4, 4));

		nodeTest.applyReward(p, null, null);
		assertEquals(p.getCoins(), 10);
	}

	@Test
	public void whiteNodeARTest() {
		Node nodeTest = new WhiteNode(null, new Position(4, 4));

		nodeTest.applyReward(p, null, null);
		assertEquals(p.getCoins(), 0);
	}

	@Test
	public void EndNodeARTest() {
		Node nodeTest = new EndNode(new Position(4, 4));

		nodeTest.applyReward(p, null, null);
		assertEquals(p.getCoins(), 0);
		assertEquals("END", nodeTest.getType());
	}

	@Test
	public void InvPesosPlusNodeTest() {
		Node nodeTest = new InvPesosPlusNode(null, new Position(4, 4));

		nodeTest.applyReward(p2, null, null);
		assertEquals((int) p2.getPesos(), (int) 120);
		assertEquals("IPP", nodeTest.getType());
	}

	@Test
	public void InvPesosMinusTest() {
		Node nodeTest = new InvPesosMinusNode(null, new Position(4, 4));

		nodeTest.applyReward(p2, null, null);
		assertEquals((int) p2.getPesos(), (int) 90);
		assertEquals("IPM", nodeTest.getType());
	}
}