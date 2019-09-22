package com.chucknorris.node.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.chucknorris.commons.Position;
import com.chucknorris.mapa.*;
import com.chucknorris.player.Player;

public class NodeTest {
	
	Player p;
	
	@Before
    public void executedBeforeEach() {
        p = new Player("Milei",0);
    }
	
	
    @Test
    public void nodeTest() {
    	Node node1 = new NodeAmarillo(null,new Position(3, 3));
    	Node node2 = new NodeRojo(null,new Position(3, 4));
    	ArrayList<Node> lista = new ArrayList<Node>();
    	lista.add(node1);
    	lista.add(node2);
    	Node nodeTest = new NodeRojo(lista,new Position(4, 4));
    	
    	assertEquals(nodeTest.getType(), "ROJO");
    	assertEquals(nodeTest.nextNodes(), lista);
    }
	
    @Test
    public void nodeRedTest() {
    	Node nodeTest = new NodeRojo(null,new Position(4, 4));
    	
    	nodeTest.applyRewards(p);
    	assertEquals(p.getCoins(), 3);
    }
	
    @Test
    public void nodeYellowTest() {
    	Node nodeTest = new NodeAmarillo(null,new Position(4, 4));
    	
    	nodeTest.applyRewards(p);
    	assertEquals(p.getCoins(), 10);
    }
	
    @Test
    public void nodeWhiteTest() {
    	Node nodeTest = new NodeWhite(null,new Position(4, 4));
    	
    	nodeTest.applyRewards(p);
    	assertEquals(p.getCoins(), 0);
    }
}