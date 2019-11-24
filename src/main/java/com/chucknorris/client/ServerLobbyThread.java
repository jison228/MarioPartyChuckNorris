package com.chucknorris.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

import com.chucknorris.Command;
import com.chucknorris.client.lobby.LobbyFrame;
import com.chucknorris.client.lobby.UpdateOrCreateLobbyResponse;
import com.chucknorris.client.sala.ClientRealSala;
import com.chucknorris.client.sala.SalaFrame;
import com.chucknorris.client.sala.UpdateOrCreateSalaResponse;
import com.chucknorris.client.tablero.MainGameScreen;
import com.google.gson.Gson;

public class ServerLobbyThread extends Thread {
	private InputStream inputStream = null;
	private Socket serverSocket = null;
	private Gson gson;
	private LobbyFrame lobbyFrame;
	private SalaFrame salaFrame;
	private UpdateOrCreateLobbyResponse respuesta;
	private String playerID;
	private ClientRealSala salaReal;
	private MainGameScreen gameFrame;
	
	public ServerLobbyThread(Socket serverSocket, String playerID) {
		this.serverSocket = serverSocket;
		this.playerID = playerID;
		gson = new Gson();
		try {
			this.inputStream = this.serverSocket.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void run() {
		// Frame del Lobby
		Scanner sc = new Scanner(inputStream);
		ChatResponse respuestaChat;
		int num;
		try {
			while ((num = inputStream.read()) > 0) {
				String hola = String.valueOf((char) num);
				hola = hola + sc.nextLine();
				Command brigadaB = gson.fromJson(hola, Command.class);
				// MARIO SANTOS, LOGISTICA Y PLANIFICACION
				switch (brigadaB.getCommandName()) {
					case "OpenLobby" :
						System.out.println("SEEEE");
						respuesta = gson.fromJson(brigadaB.getCommandJSON(), UpdateOrCreateLobbyResponse.class);
						lobbyFrame = new LobbyFrame(playerID,respuesta.usuarios, respuesta.salas, serverSocket);
						lobbyFrame.setVisible(true);
						break;
					case "UpdateLobby":
						respuesta = gson.fromJson(brigadaB.getCommandJSON(), UpdateOrCreateLobbyResponse.class);
						lobbyFrame.updateLobby(respuesta.usuarios, respuesta.salas);
						break;
					case "UpdateSala":
						salaReal = gson.fromJson(brigadaB.getCommandJSON(), ClientRealSala.class);
						salaFrame.updateSalaFrame(salaReal);
						break;
					case "LeaveSala":
						respuesta = gson.fromJson(brigadaB.getCommandJSON(), UpdateOrCreateLobbyResponse.class);
						lobbyFrame = new LobbyFrame(playerID, respuesta.usuarios, respuesta.salas, serverSocket);
						lobbyFrame.setVisible(true);
						salaFrame.dispose();
						break;
					case "ChatLobby":
						respuestaChat = gson.fromJson(brigadaB.getCommandJSON(),ChatResponse.class);
						lobbyFrame.addChatText("\"" + respuestaChat.playerID + "\" : " + respuestaChat.mensaje);
						break;
					case "SalaChat":
						respuestaChat = gson.fromJson(brigadaB.getCommandJSON(),ChatResponse.class);
						salaFrame.addChatText("\"" + respuestaChat.playerID + "\" : " + respuestaChat.mensaje);
						break;
					case "OpenSala":
						salaReal = gson.fromJson(brigadaB.getCommandJSON(), ClientRealSala.class);
						salaFrame = new SalaFrame(playerID, salaReal, serverSocket);
						salaFrame.setVisible(true);
						lobbyFrame.dispose();
						break;
					case "StartGame":
						GameInformation gameInfo = gson.fromJson(brigadaB.getCommandJSON(), GameInformation.class);
						// frame y pasarle el gameinformation
						gameFrame = new MainGameScreen(gameInfo, serverSocket);
						gameFrame.setVisible(true);
						salaFrame.dispose();
						break;
					case "TirarDado":
						gameFrame.habilitarDado();
						break;
				}
			}
			sc.close();
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
