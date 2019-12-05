package com.chucknorris.singlePlayer.gui.minigame.gameobject;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Enemy {
	public int posX;
	public abstract void update();
	public abstract void draw(Graphics g);
	public abstract Rectangle getBound();
	public abstract boolean isOutOfScreen();
}
