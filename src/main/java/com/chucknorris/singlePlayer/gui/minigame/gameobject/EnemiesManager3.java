package com.chucknorris.singlePlayer.gui.minigame.gameobject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.chucknorris.gui.minigame.util.Resource;

public class EnemiesManager3 {

	private BufferedImage cactus1;
	private BufferedImage cactus2;
	private Random rand;

	public List<Enemy> enemies;
	private MainCharacter3 mainCharacter;
	private int type;
	public int tier;
	private int dificultadIA;
	private double desvio;

	public List<Enemy> getEnemies() {
		return enemies;
	}

	public EnemiesManager3(MainCharacter3 mainCharacter, int tier, int dificultadIA) {
		rand = new Random();
		cactus1 = Resource.getResouceImage("data/jugador3/cactus1.png");
		cactus2 = Resource.getResouceImage("data/jugador3/cactus2.png");
		enemies = new ArrayList<Enemy>();
		this.mainCharacter = mainCharacter;
		enemies.add(createEnemy(0));
		this.tier = tier;
		this.dificultadIA = dificultadIA;
		this.desvio = 0;
	}

	public void update() {
		for (Enemy e : enemies) {
			e.update();
		}
		Enemy enemy = enemies.get(0);
		if (tier < dificultadIA) {
			if (tier < 1) {
				if (enemy.getBound().intersectsLine(160.0, 0.0, 160.0, 720.0)) {
					
					mainCharacter.jump();
				}
			} else if (tier < 4) {
				if (enemy.getBound().intersectsLine(220.0, 0.0, 220.0, 720.0)) {
					mainCharacter.jump();
				}
			} else if (tier < 7) {
				if (enemy.getBound().intersectsLine(300.0, 0.0, 300.0, 720.0)) {
					mainCharacter.jump();
				}

			} else {
				if (enemy.getBound().intersectsLine(325.0, 0.0, 325.0, 720.0)) {
					mainCharacter.jump();
				}
			}

		}
		if (enemy.isOutOfScreen()) {
			type++;
			mainCharacter.upScore();
			enemies.clear();
			enemies.add(createEnemy(type));
		}
	}

	public void draw(Graphics g) {
		for (Enemy e : enemies) {
			e.draw(g);
		}
	}

	private Enemy createEnemy(int type) {
		// if (enemyType = getRandom)
		type = 2;
		if (type % 2 == 0) {
			return new Cactus3(mainCharacter, 800, cactus1.getWidth() - 10, cactus1.getHeight() - 10, cactus1);
		} else {
			return new Cactus3(mainCharacter, 800, cactus2.getWidth() - 10, cactus2.getHeight() - 10, cactus2);
		}
	}

	public boolean isCollision() {
		for (Enemy e : enemies) {
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
