package com.chucknorris.node;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.chucknorris.commons.Position;
import com.chucknorris.gamemap.nodes.BoletaGasNode;
import com.chucknorris.gamemap.nodes.Node;
import com.chucknorris.player.Player;

public class BoletaGasNodeTest {
	Player p;
	Node nodeTest;

	@Before
	public void executedBeforeEach() {
		p = new Player("Cristi", 150, 100);
		nodeTest = new BoletaGasNode(null, new Position(4, 4));
	}

	@Test
	public void boletaGasRewardTest() {
		nodeTest.applyReward(p, null, null);
		assertEquals(p.printWithPesos(), p.getCharacter() + " " + "100.0");
	}

	@Test
	public void boletaGasTypeTest() {
		assertEquals("BG", nodeTest.getType());
	}
}
