package com.chucknorris.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

import com.chucknorris.Command;
import com.chucknorris.client.lobby.Lobby;
import com.google.gson.Gson;

public class ServerLobbyThread extends Thread {
	private InputStream inputStream = null;
	private Socket serverSocket = null;
	private Gson gson;
	private Lobby lobbyFrame;
	private UpdateOrCreateResponse respuesta;
	public ServerLobbyThread(Socket serverSocket) {
		this.serverSocket = serverSocket;
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
						respuesta = gson.fromJson(brigadaB.getCommandJSON(), UpdateOrCreateResponse.class);
						lobbyFrame = new Lobby(respuesta.usuarios, respuesta.salas);
						lobbyFrame.setVisible(true);
						break;
					case "UpdateLobby":
						respuesta = gson.fromJson(brigadaB.getCommandJSON(), UpdateOrCreateResponse.class);
						lobbyFrame.updateLobby(respuesta.usuarios, respuesta.salas);
						break;
					case "StartGame":
						//Recibo dos puertos
						Socket serverGameSocket = new Socket(serverSocket.getInetAddress(),0);
						Socket serverMinigameSocket = new Socket(serverSocket.getInetAddress(),1);
						ServerThread listener = new ServerThread(serverGameSocket, serverMinigameSocket);
						listener.start();
						//salaFrame.dispose();
						break;
					case "JoinSala":
						//Mostrar Sala correspondiente
						break;
					case "UpdateSala":
						//Actualizar Info Sala
						break;
					case "LeaveSala":
						//cerrar frame de Sala
						respuesta = gson.fromJson(brigadaB.getCommandJSON(), UpdateOrCreateResponse.class);
						lobbyFrame = new Lobby(respuesta.usuarios, respuesta.salas);
						lobbyFrame.setVisible(true);
						break;
					case "LobbyChat":
						respuestaChat = gson.fromJson(brigadaB.getCommandJSON(),ChatResponse.class);
						//lobbyFrame.addChatText("\"" + respuestaChat.playerID + "\" : " + respuestaChat.mensaje);
						break;
					case "SalaChat":
						respuestaChat = gson.fromJson(brigadaB.getCommandJSON(),ChatResponse.class);
						//salaFrame.addChatText("\"" + respuestaChat.playerID + "\" : " + respuestaChat.mensaje);
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
