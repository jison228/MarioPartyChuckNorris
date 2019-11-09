package com.chucknorris.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import com.chucknorris.Command;
import com.chucknorris.client.tablero.MainGameScreen;
import com.chucknorris.game.Game;
import com.chucknorris.server.ClientThread;
import com.google.gson.Gson;

public class ServerThread extends Thread {
	// private String ip;

	private InputStream inputStream = null;
	private OutputStream outputStream = null;

	private Socket clientSocket = null;
	private byte[] bytesName;
	private int maxClientsCount;
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
			MainGameScreen frame;
			Scanner sc = new Scanner(inputStream);
			int num;
			while ((num = inputStream.read()) > 0) {
				String hola = String.valueOf((char) num);
				hola = hola + sc.next();
				Command brigadaB = gson.fromJson(hola, Command.class);
				// MARIO SANTOS, LOGISTICA Y PLANIFICACION
				switch (brigadaB.getCommandName()) {
				case "MovementResponsePublic":

					break;
				case "MovementResponsePrivate":
					// PlayResponse play = new Gson().fromJson(brigadaA.getCommandJSON(),);
					break;
				case "StartGame":
					GameInformation gameInfo = gson.fromJson(brigadaB.getCommandJSON(), GameInformation.class);
					//frame y pasarle el gameinformation
					frame = new MainGameScreen(gameInfo);
					frame.setVisible(true);
					break;
				case "TirarDado":
					juego.play(juego.getPlayerList().get(juego.getCurrentTurn()));
					break;
				}
			}
			sc.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
