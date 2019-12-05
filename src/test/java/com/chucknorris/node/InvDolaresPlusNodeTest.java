package com.chucknorris.node;


import com.chucknorris.commons.Position;
import com.chucknorris.gamemap.nodes.InvDolaresPlusNode;
import com.chucknorris.gamemap.nodes.Node;
import com.chucknorris.player.Player;
import com.chucknorris.player.PlayerBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InvDolaresPlusNodeTest {
	Player p;
	Node nodeTest;

	@Before
	public void executedBeforeEach() {
		p = new PlayerBuilder()
				.setCharacter("Cristi")
				.setPesos(150)
				.setDolar(100)
				.build();

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
