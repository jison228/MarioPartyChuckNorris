package com.chucknorris.node;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.chucknorris.commons.Position;
import com.chucknorris.gamemap.nodes.InvDolaresMinusNode;
import com.chucknorris.gamemap.nodes.Node;
import com.chucknorris.player.Player;

public class InvDolaresMinusNodeTest {
	Player p;
	Node nodeTest;

	@Before
	public void executedBeforeEach() {
		p = new Player("Cristi", 150, 100);
		nodeTest = new InvDolaresMinusNode(null, new Position(4, 4));
	}

	@Test
	public void invDolaresMinusRewardTest() {
		nodeTest.applyReward(p, null, null);
		assertEquals(p.printWithDolar(), p.getCharacter() + " " + "95.0");
	}

	@Test
	public void invDolaresMinusTypeTest() {
		assertEquals("IDM", nodeTest.getType());
	}
}
