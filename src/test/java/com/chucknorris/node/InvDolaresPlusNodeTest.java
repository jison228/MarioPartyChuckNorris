package com.chucknorris.node;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.chucknorris.commons.Position;
import com.chucknorris.gamemap.nodes.InvDolaresPlusNode;
import com.chucknorris.gamemap.nodes.Node;
import com.chucknorris.player.Player;

public class InvDolaresPlusNodeTest {
	Player p;
	Node nodeTest;

	@Before
	public void executedBeforeEach() {
		p = new Player("Cristi", 150, 100);
		nodeTest = new InvDolaresPlusNode(null, new Position(4, 4));
	}

	@Test
	public void invDolaresPlusRewardTest() {
		nodeTest.applyReward(p, null, null);
		assertEquals(p.printWithDolar(), p.getCharacter() + " " + "110.0");
	}

	@Test
	public void invDolaresPlusTypeTest() {
		assertEquals("IDP", nodeTest.getType());
	}
}
