package com.chucknorris.gui.minigame.userinterface;

import java.util.Stack;

import javax.swing.JFrame;

public class ServerGameWindow extends JFrame {
	
	public static final int SCREEN_WIDTH = 800;
	private ServerGameScreen gameScreen;

	public Stack<String> listaGanadores = new Stack<String>();

	public ServerGameWindow() {
		super("Carrera de presis");
		setSize(SCREEN_WIDTH, 800);
		setLocation(400, 25);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		
		gameScreen = new ServerGameScreen(listaGanadores);
		addKeyListener(gameScreen);
		add(gameScreen);
	}
	
	public void startGame() {
		setVisible(true); //FALSE PARA QUE SE EJECUTE EN SEGUNDO PLANO (SOLO SERVER)
		gameScreen.startGame();
	}
	
	
	public static void main(String args[]) {
		(new ServerGameWindow()).startGame();
	}
}
