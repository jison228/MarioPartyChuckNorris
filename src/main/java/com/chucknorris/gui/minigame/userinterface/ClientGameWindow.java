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
	
	public void mandaleMecha(String miIdentidad) {
		this.gameScreen.mandaleMecha(miIdentidad);
	}
	
	public void minigameJumpEspert() {
		this.gameScreen.minigameJumpEspert();
	}
	
	public void minigameJumpCristina() {
		this.gameScreen.minigameJumpCristina();
	}
	
	public void minigameJumpMacri() {
		this.gameScreen.minigameJumpMacri();
	}
	
	public void minigameJumpDelCano() {
		this.gameScreen.minigameJumpDelCano();
	}
	
	public void ripEspert(int pos) {
		this.gameScreen.ripEspert(pos);
	}
	
	public void ripMacri(int pos) {
		this.gameScreen.ripMacri(pos);
	}
	
	public void ripCristina(int pos) {
		this.gameScreen.ripCristina(pos);
	}
	
	public void ripDelCano(int pos) {
		this.gameScreen.ripDelCano(pos);
	}
	
	public static void main(String args[]) {
//		(new ClientGameWindow()).startGame();
	}
}