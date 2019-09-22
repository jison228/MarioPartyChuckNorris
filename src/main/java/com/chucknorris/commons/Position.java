package com.chucknorris.commons;

public class Position {
	int posX;
	int posY;
	
	public Position(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	public void setPos(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	public int getX() {
		return this.posX;
	}
	public int getY() {
		return this.posY;
	}
}
