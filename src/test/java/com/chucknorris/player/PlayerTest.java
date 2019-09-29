package com.chucknorris.player;

import com.chucknorris.commons.Position;
import com.chucknorris.mapa.Node;
import com.chucknorris.mapa.RedNode;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PlayerTest {
    @Test
    public void test() {
        Player test = new Player("Cristi", 500);

        test.addCoins(50);

        ArrayList<Node> testNext = null;
        Node testNode = new RedNode(testNext, new Position(3, 5));
        test.setPos(testNode);

        assertEquals(550, test.getCoins());
        assertEquals("Cristi", test.getCharacter());
        assertEquals(new Position(3, 5), test.getPos().getPos());

    }
}