package com.chucknorris.dice;

import com.chucknorris.commons.Dice;

public class DiceTest {
	public static void main(String[] args) {
		Dice test = new Dice( 1 , 6 );
		for(int i=0 ; i <6 ; i++) {
			System.out.println(test.roll());
		}
	}
}
