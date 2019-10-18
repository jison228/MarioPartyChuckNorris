package com.chucknorris.node;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.chucknorris.commons.Position;
import com.chucknorris.gamemap.nodes.Node;
import com.chucknorris.gamemap.nodes.PowerupNode;
import com.chucknorris.player.Espert;
import com.chucknorris.player.Player;

public class PowerupNodeTest {
	Espert p;
	Node nodeTest;
	List<Player> players;

	@Before
	public void executedBeforeEach() {
		p = new Espert(150, 100);
		players = new ArrayList<Player>();
		players.add(p);
		nodeTest = new PowerupNode(null, new Position(4, 4));
	}

	@Test
	public void powerupRewardTest() {
		nodeTest.applyReward(p, players, null);
		assertEquals(100, (int) p.getPesos());
		assertEquals(75, (int) p.getDolares());
	}

	@Test
	public void powerupTypeTest() {
		assertEquals("PWR", nodeTest.getType());
	}
}
