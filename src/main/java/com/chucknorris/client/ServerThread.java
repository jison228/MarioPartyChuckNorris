package com.chucknorris.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

import com.chucknorris.Command;
import com.chucknorris.client.tablero.MainGameScreen;
import com.chucknorris.game.Game;
import com.chucknorris.gui.minigame.userinterface.ClientGameWindow;
import com.chucknorris.gui.minigame.userinterface.ServerGameWindow;
import com.google.gson.Gson;

public class ServerThread extends Thread {
	// private String ip;

	private InputStream inputStream = null;
	private OutputStream outputStream = null;
	private ClientGameWindow minijuego;

	private Socket clientSocket = null;

	Game juego;

	public ServerThread(Socket serverSocket, Game juego) {
		this.clientSocket = serverSocket;

		this.juego = juego;


		try {
			this.inputStream = this.clientSocket.getInputStream();
			this.outputStream = this.clientSocket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			Gson gson = new Gson();

//			Command brigadaA = new Command("TirarDado", "");
//			String send = gson.toJson(brigadaA);
			MainGameScreen frame = null;
			Scanner sc = new Scanner(inputStream);
			int num;
			while ((num = inputStream.read()) > 0) {
				String hola = String.valueOf((char) num);
				hola = hola + sc.nextLine();
				Command brigadaB = gson.fromJson(hola, Command.class);
				// MARIO SANTOS, LOGISTICA Y PLANIFICACION
				switch (brigadaB.getCommandName()) {
				case "MovementResponsePublic":
					MovementResponsePublic respuesta = gson.fromJson(brigadaB.getCommandJSON(), MovementResponsePublic.class);
					frame.playTurnPublic(respuesta);
					break;
				case "StartMinigame":
					minijuego = new ClientGameWindow();
					minijuego.startGame();
					break;
				case "MovementResponsePrivate":
					MovementResponsePrivate respuesta1 = gson.fromJson(brigadaB.getCommandJSON(), MovementResponsePrivate.class);
					frame.playTurnPrivate(respuesta1);
					break;
				case "StartGame":
					GameInformation gameInfo = gson.fromJson(brigadaB.getCommandJSON(), GameInformation.class);
					//frame y pasarle el gameinformation
					frame = new MainGameScreen(gameInfo,clientSocket);
					frame.setVisible(true);
					break;
				case "TirarDado":
					frame.habilitarDado();
					break;
				}
			}
			sc.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
