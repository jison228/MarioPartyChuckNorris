package com.chucknorris.player;

import com.chucknorris.commons.Position;
import com.chucknorris.gamemap.nodes.Node;
import com.chucknorris.gamemap.nodes.RedNode;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PlayerTest {
	Player playerTest;
	Player playerTest2;

	@Before
	public void executedBeforeEach() {
		playerTest = new PlayerBuilder()
				.setCharacter("Cristi")
				.setCoins(500)
				.build();

		playerTest2 = new PlayerBuilder()
				.setCharacter("Mauri")
				.setCoins(500)
				.setPesos(500)
				.setDolar(500)
				.setSalario(500)
				.build();
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
		assertEquals("Cristi 550", playerTest.toString());
	}

	@Test
	public void setPosTest() {
		ArrayList<Node> testNext = null;
		Node testNode = new RedNode(testNext, new Position(3, 5));
		playerTest.setNodeLocation(testNode);

		assertEquals(new Position(3, 5), playerTest.getNodeLocation().getPositionCoords());
	}

	@Test
	public void addPesosTest() {
		playerTest2.addPesos(50);
		assertEquals(550, (int) playerTest2.getPesos());
	}

	@Test
	public void substractPesosTest() {
		playerTest2.substractPesos(50);
		assertEquals(450, (int) playerTest2.getPesos());
	}

	@Test
	public void substractDolarTest() {
		playerTest2.substractDolar(50);
		assertEquals(450, (int) playerTest2.getDolares());
	}

	@Test
	public void addDolarTest() {
		playerTest2.addDolar(50);
		assertEquals(550, (int) playerTest2.getDolares());
	}

	@Test
	public void addPesosPercentageTest() {
		playerTest2.addPesosByPercentage(10);
		assertEquals(550, (int) playerTest2.getPesos());
	}

	@Test
	public void addDolaresPercentageTest() {
		playerTest2.addDolaresByPercentage(10);
		assertEquals(550, (int) playerTest2.getDolares());
	}

	@Test
	public void addSalarioPercentageTest() {
		playerTest2.addSalarioByPercentage(10);
		assertEquals(550, (int) playerTest2.getSalario());
	}

	@Test
	public void cobrarSalarioTest() {
		playerTest2.cobrarSalario();
		assertEquals(1000, (int) playerTest2.getPesos());
	}

	@Test
	public void buyDolaresTest() {
		playerTest2.buyDolares(50, 50);
		assertEquals(450, (int) playerTest2.getPesos());
		assertEquals(550, (int) playerTest2.getDolares());
	}

}