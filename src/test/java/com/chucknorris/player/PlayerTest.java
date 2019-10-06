package com.chucknorris.player;

import com.chucknorris.commons.Position;
import com.chucknorris.gamemap.Node;
import com.chucknorris.gamemap.RedNode;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

public class PlayerTest {
	Player playerTest;

	@Before
	public void executedBeforeEach() {
		playerTest = new Player("Cristi", 500);
	}

	@Test
	public void constructorTest() {
		assertEquals(500, playerTest.getCoins());
		assertEquals("Cristi", playerTest.getCharacter());
	}

	@Test
	public void addCoinsTest() {
		playerTest.addCoins(50);

		assertEquals(550, playerTest.getCoins());
	}

	@Test
	public void setPosTest() {
		ArrayList<Node> testNext = null;
		Node testNode = new RedNode(testNext, new Position(3, 5));
		playerTest.setPos(testNode);

		assertEquals(new Position(3, 5), playerTest.getPos().getPos());

	}
}