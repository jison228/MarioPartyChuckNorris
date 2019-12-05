package com.chucknorris.gui.minigame.userinterface;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.Semaphore;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JPanel;

import com.chucknorris.Command;
import com.chucknorris.gui.minigame.gameobject.Clouds;
import com.chucknorris.gui.minigame.gameobject.Clouds2;
import com.chucknorris.gui.minigame.gameobject.Clouds3;
import com.chucknorris.gui.minigame.gameobject.Clouds4;
import com.chucknorris.gui.minigame.gameobject.EnemiesManager;
import com.chucknorris.gui.minigame.gameobject.EnemiesManager2;
import com.chucknorris.gui.minigame.gameobject.EnemiesManager3;
import com.chucknorris.gui.minigame.gameobject.EnemiesManager4;
import com.chucknorris.gui.minigame.gameobject.Land;
import com.chucknorris.gui.minigame.gameobject.Land2;
import com.chucknorris.gui.minigame.gameobject.Land3;
import com.chucknorris.gui.minigame.gameobject.Land4;
import com.chucknorris.gui.minigame.gameobject.MainCharacter;
import com.chucknorris.gui.minigame.gameobject.MainCharacter2;
import com.chucknorris.gui.minigame.gameobject.MainCharacter3;
import com.chucknorris.gui.minigame.gameobject.MainCharacter4;
import com.chucknorris.gui.minigame.util.Resource;
import com.chucknorris.player.Player;
import com.chucknorris.server.BifurcationResponse;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class ClientGameScreen extends JPanel implements Runnable, KeyListener {

	private static final int START_GAME_STATE = 0;
	private static final int GAME_PLAYING_STATE = 1;
	private static final int GAME_OVER_STATE = 2;
	public String test;
	private Land land;
	private Land2 land2;
	private Land3 land3;
	private Land4 land4;
	private MainCharacter mainCharacter;
	private MainCharacter2 mainCharacter2;
	private MainCharacter3 mainCharacter3;
	private MainCharacter4 mainCharacter4;
	private EnemiesManager enemiesManager;
	private EnemiesManager2 enemiesManager2;
	private EnemiesManager3 enemiesManager3;
	private EnemiesManager4 enemiesManager4;
	private Clouds clouds;
	private Clouds2 clouds2;
	private Clouds3 clouds3;
	private Clouds4 clouds4;
	private Thread thread;
	private AudioClip musica1;
	private boolean isKeyPressed;
	private int gameState = START_GAME_STATE;
	private AudioInputStream stream;
	private BufferedImage hardstyleImage;
	private BufferedImage gameOverButtonImage;
	private InputStream inputStream = null;
	private OutputStream outputStream = null;
	private Graphics g;
	private String miIdentidad;
	private int[] tiersScore = new int[10];
	private int[] tiersSpeed = new int[10];
	private int tier;
	private Socket serverSocket;
	public Stack<String> listaGanadores;
	private Gson gson = new Gson();
	private ClientGameWindow frame;
	private boolean alive;

	public ClientGameScreen(Stack<String> listaGanadores, Socket serverSocket, InputStream inputStream,
			ClientGameWindow frame) {
		this.alive = true;
		this.frame = frame;
		this.inputStream = inputStream;
		this.serverSocket = serverSocket;
		this.listaGanadores = listaGanadores;
		tiersScore[0] = 200;

		tier = 0;
		for (int i = 1; i < 10; i++) {
			tiersScore[i] += tiersScore[i - 1] + 200;
		}
		tiersSpeed[0] = 6;
		for (int j = 1; j < 10; j++) {
			tiersSpeed[j] += tiersSpeed[j - 1] + 2;
		}
		mainCharacter = new MainCharacter();
		mainCharacter2 = new MainCharacter2();
		mainCharacter3 = new MainCharacter3();
		mainCharacter4 = new MainCharacter4();
		mainCharacter.setSpeedX(4);
		mainCharacter2.setSpeedX(4);
		mainCharacter3.setSpeedX(4);
		mainCharacter4.setSpeedX(4);
		land = new Land(ClientGameWindow.SCREEN_WIDTH, mainCharacter);
		land2 = new Land2(ClientGameWindow.SCREEN_WIDTH, mainCharacter2);
		land3 = new Land3(ClientGameWindow.SCREEN_WIDTH, mainCharacter3);
		land4 = new Land4(ClientGameWindow.SCREEN_WIDTH, mainCharacter4);
		hardstyleImage = Resource.getResouceImage("data/hardstyle.png");
		gameOverButtonImage = Resource.getResouceImage("data/gameover_text.png");
		enemiesManager = new EnemiesManager(mainCharacter);
		enemiesManager2 = new EnemiesManager2(mainCharacter2);
		enemiesManager3 = new EnemiesManager3(mainCharacter3);
		enemiesManager4 = new EnemiesManager4(mainCharacter4);
		clouds = new Clouds(ClientGameWindow.SCREEN_WIDTH, mainCharacter);
		clouds2 = new Clouds2(ClientGameWindow.SCREEN_WIDTH, mainCharacter2);
		clouds3 = new Clouds3(ClientGameWindow.SCREEN_WIDTH, mainCharacter3);
		clouds4 = new Clouds4(ClientGameWindow.SCREEN_WIDTH, mainCharacter4);
//		try {
//			musica1 = Applet.newAudioClip(new URL("file", "", "data/musica1.wav"));
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		}
		try {
			File yourFile = new File("data/musica1.wav");
			AudioFormat format;
			DataLine.Info info;
			stream = AudioSystem.getAudioInputStream(yourFile);
			format = stream.getFormat();
			info = new DataLine.Info(Clip.class, format);
			clip = (Clip) AudioSystem.getLine(info);
		} catch (Exception e) {
			// whatevers
		}
	}

	public void startGame() {
		thread = new Thread(this);
		thread.start();
	}

	private int posicionJugador1;
	private int posicionJugador2;
	private int posicionJugador3;
	private int posicionJugador4;

	public void gameUpdate() {
		if (gameState == GAME_PLAYING_STATE) {
			clouds.update();
			clouds2.update();
			clouds3.update();
			clouds4.update();
			land.update();
			land2.update();
			land3.update();
			land4.update();
			mainCharacter.update();
			mainCharacter2.update();
			mainCharacter3.update();
			mainCharacter4.update();
			enemiesManager.update();
			enemiesManager2.update();
			enemiesManager3.update();
			enemiesManager4.update();
			if (mainCharacter.score == tiersScore[tier] || mainCharacter2.score == tiersScore[tier]
					|| mainCharacter3.score == tiersScore[tier] || mainCharacter4.score == tiersScore[tier]) {
				mainCharacter.setSpeedX(tiersSpeed[tier]);
				mainCharacter2.setSpeedX(tiersSpeed[tier]);
				mainCharacter3.setSpeedX(tiersSpeed[tier]);
				mainCharacter4.setSpeedX(tiersSpeed[tier]);
				if (tier < 9)
					tier++;
			}

			if (alive && miIdentidad!=null) {
				switch (miIdentidad) {
				case "Espert":
					// AVISARLE AL SERVER QUE MORI
					if (enemiesManager.isCollision()) {
						alive = false;
						PrintStream ps = null;
						try {
							ps = new PrintStream(serverSocket.getOutputStream(), true);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						Command bif = new Command("MeMoriSoyEspert", String.valueOf(mainCharacter.score));
						String send = gson.toJson(bif);
						ps.println(send);
						// FIN DE AVISADA DE ME MORI
					}
					break;
				case "Macri":

					if (enemiesManager3.isCollision()) {
						alive = false;
						PrintStream ps = null;
						try {
							ps = new PrintStream(serverSocket.getOutputStream(), true);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						Command bif = new Command("MeMoriSoyMacri", String.valueOf(mainCharacter3.score));
						String send = gson.toJson(bif);
						ps.println(send);
						// FIN DE AVISADA DE ME MORI
					}
					break;
				case "Cristina":

					if (enemiesManager2.isCollision()) {
						alive = false;
						PrintStream ps = null;
						try {
							ps = new PrintStream(serverSocket.getOutputStream(), true);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						Command bif = new Command("MeMoriSoyCristina", String.valueOf(mainCharacter2.score));
						String send = gson.toJson(bif);
						ps.println(send);
						// FIN DE AVISADA DE ME MORI

					}
					break;
				case "Del Ca�o":
					if (enemiesManager4.isCollision()) {
						alive = false;
						PrintStream ps = null;
						try {
							ps = new PrintStream(serverSocket.getOutputStream(), true);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						Command bif = new Command("MeMoriSoyDelCa�o", String.valueOf(mainCharacter4.score));
						String send = gson.toJson(bif);
						ps.println(send);
						// FIN DE AVISADA DE ME MORI

					}
					break;
				}
			}

		}
	}

	/*
						
						
	*/
	public void paint(Graphics e) {
		this.g = e;
		g.setColor(Color.decode("#f7f7f7"));
		g.fillRect(0, 0, getWidth(), getHeight());
		switch (gameState) {
		case START_GAME_STATE:
			mainCharacter.draw(g);
			mainCharacter2.draw(g);
			mainCharacter3.draw(g);
			mainCharacter4.draw(g);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Courier New", Font.BOLD, 14));
			g.drawString("Salte con 'A'", 200, 100);
			g.drawString("Salte con 'B'", 200, 300);
			g.drawString("Salte con 'P'", 200, 500);
			g.drawString("Salte con '.'", 200, 700);
			g.setFont(new Font("Tahoma", Font.BOLD, 20));
			g.drawString("Presionar espacio y A SALTAR", 300, 400);
			break;
		case GAME_PLAYING_STATE:
			if (mainCharacter.score > 1660 || mainCharacter2.score > 1660 || mainCharacter3.score > 1660
					|| mainCharacter4.score > 1660)
				g.drawImage(hardstyleImage, 0, 0, getWidth(), getHeight(), null);

		case GAME_OVER_STATE:
			clouds.draw(g);
			clouds2.draw(g);
			clouds3.draw(g);
			clouds4.draw(g);
			land.draw(g);
			land2.draw(g);
			land3.draw(g);
			land4.draw(g);
			enemiesManager.draw(g);
			enemiesManager2.draw(g);
			enemiesManager3.draw(g);
			enemiesManager4.draw(g);
			mainCharacter.draw(g);
			mainCharacter2.draw(g);
			mainCharacter3.draw(g);
			mainCharacter4.draw(g);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Courier New", Font.BOLD, 14));
			g.drawString("Puntaje " + mainCharacter.score, 500, 20);
			g.drawString("Puntaje " + mainCharacter2.score, 500, 220);
			g.drawString("Puntaje " + mainCharacter3.score, 500, 420);
			g.drawString("Puntaje " + mainCharacter4.score, 500, 620);
			if (gameState == GAME_OVER_STATE) {
				g.drawImage(gameOverButtonImage, 200, 180, null);
				g.setColor(Color.BLACK);
				g.setFont(new Font("Courier New", Font.BOLD, 16));
				g.drawString("Posicion:" + posicionJugador1, 600, 50);
				g.drawString("Posicion:" + posicionJugador2, 600, 250);
				g.drawString("Posicion:" + posicionJugador3, 600, 450);
				g.drawString("Posicion:" + posicionJugador4, 600, 650);
			}
			break;
		}
	}

	@Override
	public void run() {

		int fps = 100;
		long msPerFrame = 1000 * 1000000 / fps;
		long lastTime = 0;
		long elapsed;

		int msSleep;
		int nanoSleep;

		long endProcessGame;
		long lag = 0;
		while (gameState == GAME_PLAYING_STATE || gameState == START_GAME_STATE) {
//			gameState = GAME_PLAYING_STATE; PARA QUE FUNCIONE EN SEGUNDO PLANO
			gameUpdate();
			repaint();
			semaphore.acquireUninterruptibly();
			analizar();
			semaphore.release();
			endProcessGame = System.nanoTime();
			elapsed = (lastTime + msPerFrame - System.nanoTime());
			msSleep = (int) (elapsed / 1000000);
			nanoSleep = (int) (elapsed % 1000000);
			if (msSleep <= 0) {
				lastTime = System.nanoTime();
				continue;
			}
			try {
				Thread.sleep(msSleep, nanoSleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			lastTime = System.nanoTime();
		}
		if (gameState == GAME_OVER_STATE) {

		}
	}

	private Queue<Character> pressed = new LinkedList<Character>();
	static Semaphore semaphore = new Semaphore(1);
	private Clip clip;

	private void analizar() {
		while (!pressed.isEmpty()) {
			Character character = pressed.poll();
			if (character == ' ' && alive) {
				PrintStream ps = null;
				try {
					ps = new PrintStream(serverSocket.getOutputStream(), true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Command bif = new Command("JumpMinigame", this.miIdentidad);
				String send = gson.toJson(bif);
				if (miIdentidad != null)
					ps.println(send);
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		semaphore.acquireUninterruptibly();
		// A NISMAN LO MATARON
		pressed.add(e.getKeyChar());
		if (!isKeyPressed) {
			isKeyPressed = true;
			switch (gameState) {
			case START_GAME_STATE:
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
//					gameState = GAME_PLAYING_STATE;
					PrintStream ps = null;
					try {
						ps = new PrintStream(serverSocket.getOutputStream(), true);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Command bif = new Command("MandaleMecha", "Amiguerou");
					String send = gson.toJson(bif);
					ps.println(send);
				}
				break;
			case GAME_PLAYING_STATE:
				break;
			case GAME_OVER_STATE:
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
//					gameState = GAME_PLAYING_STATE;
//					resetGame();
				}
				break;
			}
		}
		semaphore.release();

	}

	@Override
	public void keyReleased(KeyEvent e) {
		isKeyPressed = false;
		if (gameState == GAME_PLAYING_STATE) {
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				mainCharacter.down(false);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void mandaleMecha(String miIdentidad) {

		this.miIdentidad = miIdentidad;
		System.out.println(miIdentidad);
		// musica1.play();
		try {
			clip.open(stream);
		} catch (LineUnavailableException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		clip.start();
		gameState = GAME_PLAYING_STATE;
		isKeyPressed = true;

	}

	public void minigameJumpEspert() {
		mainCharacter.jump();
	}

	public void minigameJumpCristina() {
		mainCharacter2.jump();
	}

	public void minigameJumpMacri() {
		mainCharacter3.jump();
	}

	public void minigameJumpDelCano() {
		mainCharacter4.jump();
	}

	public void ripEspert(int pos) {
		mainCharacter.playDeadSound();
		posicionJugador1 = pos;
		mainCharacter.dead(true);
		mainCharacter.setSpeedX(0);
		// estanMuertos();
	}

	public void ripMacri(int pos) {
		mainCharacter3.playDeadSound();
		posicionJugador3 = pos;
		mainCharacter3.dead(true);
		mainCharacter3.setSpeedX(0);
		// estanMuertos();
	}

	public void ripCristina(int pos) {
		mainCharacter2.playDeadSound();
		posicionJugador2 = pos;
		mainCharacter2.dead(true);
		mainCharacter2.setSpeedX(0);
		// estanMuertos();
	}

	public void ripDelCano(int pos) {
		mainCharacter4.playDeadSound();
		posicionJugador4 = pos;
		mainCharacter4.dead(true);
		mainCharacter4.setSpeedX(0);
		// estanMuertos();
	}
	
	public void stopMusic() {
		clip.close();
	}
	/*
	 * private void estanMuertos() { if (mainCharacter.getState() == 3 &&
	 * mainCharacter2.getState() == 3 && mainCharacter3.getState() == 3 &&
	 * mainCharacter4.getState() == 3) { System.out.println("entra?"); gameState =
	 * GAME_OVER_STATE; // musica1.stop(); try { Thread.sleep(5000); } catch
	 * (InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } frame.dispose(); clip.close(); } }
	 */
	/*
	 * public void cerrate() { gameState = GAME_OVER_STATE; }
	 */
}
