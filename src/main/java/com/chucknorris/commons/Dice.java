package com.chucknorris.commons;

import java.util.Random;

public class Dice {
	private int min;
	private int max;
	
	public Dice(int min, int max) {
		this.min = min;
		this.max = max;
	}

	public int roll(){
		 Random r = new Random();
	     return r.nextInt((max - min) + 1) + min;
	}
}
