package com.chucknorris.node;

import com.chucknorris.commons.Position;
import com.chucknorris.gamemap.nodes.InvPesosPlusNode;
import com.chucknorris.gamemap.nodes.Node;
import com.chucknorris.player.Player;
import com.chucknorris.player.PlayerBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InvPesosPlusNodeTest {
	Player p;
	Node nodeTest;

	@Before
	public void executedBeforeEach() {
		p = new PlayerBuilder()
				.setCharacter("Cristi")
				.setPesos(100)
				.setDolar(150)
				.build();

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
