package com.chucknorris.gui.minigame.userinterface;

import java.io.InputStream;
import java.net.Socket;
import java.util.Stack;

import javax.swing.JFrame;

public class ClientGameWindow extends JFrame {
	
	public static final int SCREEN_WIDTH = 800;
	private ClientGameScreen gameScreen;
	private Socket serverSocket;
	public Stack<String> listaGanadores = new Stack<String>();

	public ClientGameWindow(Socket serverSocket,InputStream inputStream) {
		super("Carrera de presis");
		this.serverSocket = serverSocket;
		setSize(SCREEN_WIDTH, 800);
		setLocation(400, 25);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		
		gameScreen = new ClientGameScreen(listaGanadores,serverSocket,inputStream,this);
		addKeyListener(gameScreen);
		add(gameScreen);
	}
	
	public void startGame() {
		setVisible(true); //FALSE PARA QUE SE EJECUTE EN SEGUNDO PLANO (SOLO SERVER)
		gameScreen.startGame();
	}
	
	
	public static void main(String args[]) {
		(new ClientGameWindow(null,null)).startGame();
	}
}