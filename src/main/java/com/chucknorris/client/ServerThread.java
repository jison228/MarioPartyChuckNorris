package com.chucknorris.client;

import java.io.IOException;
import java.io.InputStream;
//import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

import com.chucknorris.Command;
import com.chucknorris.client.endgame.EndgameFrame;
import com.chucknorris.client.tablero.MainGameScreen;
import com.chucknorris.gui.minigame.userinterface.ClientGameWindow;
import com.google.gson.Gson;

public class ServerThread extends Thread {

	private InputStream inputStream = null;
	//private OutputStream outputStream = null;
	
	private InputStream inputStreamMG = null;
	private ClientGameWindow minijuego;

	private Socket clientSocket = null;
	private Socket clientSocketMinigame = null;

	public ServerThread(Socket serverSocket, Socket serverSocketMinigame) {
		this.clientSocket = serverSocket;
		this.clientSocketMinigame = serverSocketMinigame;
		try {
			this.inputStream = this.clientSocket.getInputStream();
			//this.outputStream = this.clientSocket.getOutputStream();
			this.inputStreamMG = this.clientSocketMinigame.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			Gson gson = new Gson();

			MainGameScreen frame = null;
			Scanner sc = new Scanner(inputStream);
			int num;
			while ((num = inputStream.read()) > 0) {
				String hola = String.valueOf((char) num);
				hola = hola + sc.nextLine();
				Command brigadaB = gson.fromJson(hola, Command.class);
				// MARIO SANTOS, LOGISTICA Y PLANIFICACION
				switch (brigadaB.getCommandName()) {
				case "Chat":
					ChatResponse respuestaChat = gson.fromJson(brigadaB.getCommandJSON(),ChatResponse.class);
					frame.addChatText("\"" + respuestaChat.playerID + "\" : " + respuestaChat.mensaje);
					break;
				case "StartMinigame":
					minijuego = new ClientGameWindow(clientSocketMinigame, inputStreamMG);
					minijuego.startGame();
					break;
				case "EndGame":
					new EndgameFrame(brigadaB.getCommandJSON(),null).setVisible(true);
					frame.dispose();
					break;
				case "Compra":
					ActualizarCompraResponse respuestaCompra = gson.fromJson(brigadaB.getCommandJSON(),
							ActualizarCompraResponse.class);
					frame.updateAfterCompra(respuestaCompra.lista);
					break;
				case "MovementResponsePublic":
					MovementResponsePublic respuestaMovPub = gson.fromJson(brigadaB.getCommandJSON(),
							MovementResponsePublic.class);
					frame.playTurnPublic(respuestaMovPub);
					break;
				case "MovementResponsePrivate":
					MovementResponsePrivate respuestaMovPriv = gson.fromJson(brigadaB.getCommandJSON(),
							MovementResponsePrivate.class);
					frame.playTurnPrivate(respuestaMovPriv);
					break;
				case "StartGame":
					GameInformation gameInfo = gson.fromJson(brigadaB.getCommandJSON(), GameInformation.class);
					// frame y pasarle el gameinformation
					frame = new MainGameScreen(gameInfo, clientSocket);
					frame.setVisible(true);
					break;
				case "TirarDado":
					frame.habilitarDado();
					break;
				case "EndTurn":
					EndTurnResponse respuestaEndTurn = gson.fromJson(brigadaB.getCommandJSON(), EndTurnResponse.class);
					frame.endTurnIndeed(respuestaEndTurn);
					break;
				}
			}
			sc.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
