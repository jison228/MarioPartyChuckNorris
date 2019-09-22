package com.chucknorris.commons;

import java.util.Random;

public class Dice {
	private int min;
	private int max;
	
	public int roll(){
		 Random r = new Random();
	     return r.nextInt((max - min) + 1) + min;
	}
}
