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
						salaReal = gson.fromJson(brigadaB.getCommandJSON(), ClientRealSala.class);
						salaFrame.updateSalaFrame(salaReal);
						break;
					case "LeaveSala":
						//cerrar frame de Sala
						respuesta = gson.fromJson(brigadaB.getCommandJSON(), UpdateOrCreateLobbyResponse.class);
						lobbyFrame = new LobbyFrame(playerID, respuesta.usuarios, respuesta.salas, serverSocket);
						lobbyFrame.setVisible(true);
						break;
					case "ChatLobby":
						respuestaChat = gson.fromJson(brigadaB.getCommandJSON(),ChatResponse.class);
						lobbyFrame.addChatText("\"" + respuestaChat.playerID + "\" : " + respuestaChat.mensaje);
						break;
					case "SalaChat":
						respuestaChat = gson.fromJson(brigadaB.getCommandJSON(),ChatResponse.class);
						//salaFrame.addChatText("\"" + respuestaChat.playerID + "\" : " + respuestaChat.mensaje);
						break;
					case "OpenSala":
						salaReal = gson.fromJson(brigadaB.getCommandJSON(), ClientRealSala.class);
						salaFrame = new SalaFrame(playerID, salaReal, serverSocket);
						salaFrame.setVisible(true);
						lobbyFrame.dispose();
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
