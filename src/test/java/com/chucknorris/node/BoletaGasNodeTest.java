package com.chucknorris.node;

import com.chucknorris.commons.Position;
import com.chucknorris.gamemap.nodes.BoletaGasNode;
import com.chucknorris.gamemap.nodes.Node;
import com.chucknorris.player.Player;
import com.chucknorris.player.PlayerBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoletaGasNodeTest {
	Player p;
	Node nodeTest;

	@Before
	public void executedBeforeEach() {
		p = new PlayerBuilder()
				.setCharacter("Cristi")
				.setPesos(150)
				.setDolar(100)
				.build();

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
