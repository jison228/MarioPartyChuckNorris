package com.chucknorris.dice;

import com.chucknorris.commons.Dice;
import org.junit.Assert;
import org.junit.Test;

public class DiceTest {
    @Test
    public void test_roll_result_beetwen_interval() {
        Dice test = new Dice(1, 6);

        for (int i = 1; i <= 10000; i++) {
            int rolled = test.roll();

            Assert.assertTrue(rolled >= 1 && rolled <= 6);
        }
    }
}
