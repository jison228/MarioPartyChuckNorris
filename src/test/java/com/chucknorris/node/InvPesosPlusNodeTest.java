package com.chucknorris.node;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.chucknorris.commons.Position;
import com.chucknorris.gamemap.nodes.InvPesosPlusNode;
import com.chucknorris.gamemap.nodes.Node;
import com.chucknorris.player.Player;

public class InvPesosPlusNodeTest {
	Player p;
	Node nodeTest;

	@Before
	public void executedBeforeEach() {
		p = new Player("Cristi", 100, 150);
		nodeTest = new InvPesosPlusNode(null, new Position(4, 4));
	}

	@Test
	public void invPesosPlusRewardTest() {
		nodeTest.applyReward(p, null, null);
		assertEquals(p.printWithPesos(), p.getCharacter() + " " + "120.0");
	}

	@Test
	public void invPesosPlusTypeTest() {
		assertEquals("IPP", nodeTest.getType());
	}
}
