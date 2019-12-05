package com.chucknorris.node;

import com.chucknorris.commons.Position;
import com.chucknorris.gamemap.nodes.Node;
import com.chucknorris.gamemap.nodes.ParitariaNode;
import com.chucknorris.player.Player;
import com.chucknorris.player.PlayerBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParitariaNodeTest {
	Player p;
	Node nodeTest;

	@Before
	public void executedBeforeEach() {
		p = new PlayerBuilder()
				.setCharacter("Cristi")
				.setPesos(150)
				.setDolar(100)
				.setSalario(500)
				.build();
		nodeTest = new ParitariaNode(null, new Position(4, 4));
	}

	@Test
	public void paritariaRewardTest() {
		nodeTest.applyReward(p, null, null);
		assertEquals(p.printWithSalario(), p.getCharacter() + " " + "600.0");
	}

	@Test
	public void paritariaTypeTest() {
		assertEquals("PARI", nodeTest.getType());
	}
}
