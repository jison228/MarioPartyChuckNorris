package com.chucknorris.player;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PlayerTest {
    @Test
    public void test() {
        Player test = new Player("Cristi" , 500);
        
        test.addCoins(50);
        
        assertEquals(550,test.getCoins());
        assertEquals("Cristi", test.getCharacter());
    }
}