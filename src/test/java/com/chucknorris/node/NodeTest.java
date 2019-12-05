package com.chucknorris.node;

import com.chucknorris.commons.Position;
import com.chucknorris.gamemap.nodes.*;
import com.chucknorris.player.Player;
import com.chucknorris.player.PlayerBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class NodeTest {

	Player p;

	@Before
	public void executedBeforeEach() {
		p = new PlayerBuilder()
				.setCharacter("Cristi")
				.build();
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
		Node nodeTest = new EndNode(null, new Position(4, 4));

		nodeTest.applyReward(p, null, null);
		assertEquals(p.getCoins(), 0);
		assertEquals("END", nodeTest.getType());
	}

}