package com.chucknorris.gui.minigame.userinterface;

import java.util.Stack;

import javax.swing.JFrame;

public class GameWindow extends JFrame {
	
	public static final int SCREEN_WIDTH = 800;
	private GameScreen gameScreen;
	public Stack<String> listaGanadores = new Stack<String>();
	
	public GameWindow() {
		super("Carrera de presis");
		setSize(SCREEN_WIDTH, 800);
		setLocation(400, 150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		gameScreen = new GameScreen(listaGanadores);
		addKeyListener(gameScreen);
		add(gameScreen);
	}
	
	public void startGame() {
		setVisible(true);
		gameScreen.startGame();
	}
	
	
	public static void main(String args[]) {
		(new GameWindow()).startGame();
	}
}
