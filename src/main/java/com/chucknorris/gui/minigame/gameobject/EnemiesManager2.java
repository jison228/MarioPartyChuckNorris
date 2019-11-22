package com.chucknorris.gui.minigame.gameobject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.chucknorris.gui.minigame.util.Resource;

public class EnemiesManager2 {
	
	private BufferedImage cactus1;
	private BufferedImage cactus2;
	private Random rand;
	private int type=0;
	private List<Enemy> enemies;
	private MainCharacter2 mainCharacter;
	
	public EnemiesManager2(MainCharacter2 mainCharacter) {
		rand = new Random();
		cactus1 = Resource.getResouceImage("data/jugador2/cactus1.png");
		cactus2 = Resource.getResouceImage("data/jugador2/cactus2.png");
		enemies = new ArrayList<Enemy>();
		this.mainCharacter = mainCharacter;
		enemies.add(createEnemy(0));
	}
	
	public void update() {
		for(Enemy e : enemies) {
			e.update();
		}
		Enemy enemy = enemies.get(0);
		if(enemy.isOutOfScreen()) {
			type++;
			mainCharacter.upScore();
			enemies.clear();
			enemies.add(createEnemy(type));
		}
	}
	
	public void draw(Graphics g) {
		for(Enemy e : enemies) {
			e.draw(g);
		}
	}
	
	private Enemy createEnemy(int type) {
		// if (enemyType = getRandom)
		//int type = rand.nextInt(2);
				//int type=0;
		if(type%2== 0) {
			return new Cactus2(mainCharacter, 800, cactus1.getWidth() - 10, cactus1.getHeight() - 10, cactus1);
		} else {
			return new Cactus2(mainCharacter, 800, cactus2.getWidth() - 10, cactus2.getHeight() - 10, cactus2);
		}
	}
	
	public boolean isCollision() {
		for(Enemy e : enemies) {
			if (mainCharacter.getBound().intersects(e.getBound())) {
				return true;
			}
		}
		return false;
	}
	
	public void reset() {
		enemies.clear();
		enemies.add(createEnemy(0));
	}
	
}
