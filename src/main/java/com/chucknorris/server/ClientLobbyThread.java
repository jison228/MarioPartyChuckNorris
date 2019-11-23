package com.chucknorris.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.chucknorris.Command;
import com.chucknorris.User;
import com.chucknorris.client.ChatResponse;
import com.chucknorris.client.ClientLobbySala;
import com.chucknorris.client.UpdateOrCreateResponse;
import com.google.gson.Gson;

public class ClientLobbyThread extends Thread {
	private Gson gson;
	private InputStream inputStream = null;
	private OutputStream outputStream = null;

	private Socket clientSocket = null;
	private String playerID;
	private Map<String, ClientLobbyThread> threadsMap;
	private Map<String, Sala> salas;

	public ClientLobbyThread(String playerID, Socket clientSocket, Map<String, ClientLobbyThread> threadsMap,
			Map<String, Sala> salas) {
		this.clientSocket = clientSocket;
		this.threadsMap = threadsMap;
		this.playerID = playerID;
		this.salas = salas;
		gson = new Gson();
		try {
			inputStream = this.clientSocket.getInputStream();
			outputStream = this.clientSocket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {

		try {
			Scanner sc = new Scanner(inputStream);
			int num;
			String usersMessage;
			while ((num = inputStream.read()) > 0) {
				String hola = String.valueOf((char) num);
				hola = hola + sc.nextLine();
				Command brigadaB = gson.fromJson(hola, Command.class);
				switch (brigadaB.getCommandName()) {
				case "SwitchFromPlayerToSpectator":
					this.salas.get(brigadaB.getCommandJSON()).players.remove(playerID);
					// Avisarle a los demas que actualicen la sala
				case "SwitchFromSpectatorToPlayer":
					this.salas.get(brigadaB.getCommandJSON()).players.add(playerID);
					// Avisarle a los demas que actualicen la sala
				case "JoinSala": // Añadir GSON // commando
					if (this.salas.get(brigadaB.getCommandJSON()).players.size() < 4)
						this.salas.get(brigadaB.getCommandJSON()).players.add(playerID);
					this.salas.get(brigadaB.getCommandJSON()).threadsMap.put(playerID, this);// sala recibida por//
																								// commando
					threadsMap.remove(playerID);
					usersMessage = gson.toJson(createLobbyResponse(threadsMap, salas));
					for (Map.Entry<String, ClientLobbyThread> entry : threadsMap.entrySet()) {
						this.send(new Command("UpdateLobby", usersMessage), entry.getKey());
					}
					// Mandarle al jugador la info sobre la sala
					// Avisarle a los de la sala que actualicen
					break;
				case "CreateSala":
					if (salas.size() < 4) {
						Sala nuevaSala = new Sala(brigadaB.getCommandJSON());
						nuevaSala.players.add(playerID);
						nuevaSala.threadsMap.put(playerID, this);
						this.salas.put(brigadaB.getCommandJSON(), nuevaSala);
						threadsMap.remove(playerID);
						usersMessage = gson.toJson(createLobbyResponse(threadsMap, salas));
						for (Map.Entry<String, ClientLobbyThread> entry : threadsMap.entrySet()) {
							this.send(new Command("UpdateLobby", usersMessage), entry.getKey());
						}
						// Enviarle al player la info de la sala
					} else {
						// Avisarle que es un pelotudo
					}
					break;
				case "LeaveSala":
					threadsMap.put(playerID, this);
					this.salas.get(brigadaB.getCommandJSON()).threadsMap.remove(playerID);
					this.salas.get(brigadaB.getCommandJSON()).players.remove(playerID);
					// Avisarle a los de la sala que actualicen
					// Avisarles a los del lobby que actualicen
					break;
				case "Chat":
					ChatResponse respuesta = new ChatResponse(playerID, brigadaB.getCommandJSON());
					for (Map.Entry<String, ClientLobbyThread> entry : threadsMap.entrySet()) {
						this.send(new Command("Chat", gson.toJson(respuesta)), entry.getKey());
					}
					break;
				// Pensar caso de exit Lobby
				}
			}
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
			if (threadsMap.remove(playerID) == null) {
				/*
				 * for(int i = 0; i < salas.size();i++) {
				 * if(salas.get(i).players.remove(playerID)) { //Avisar a los de la sala que
				 * actualicen } }
				 */ // Para Pensar
			}
		}

	}

	public void send(Command send, String playerID) throws IOException {
		String mensaje = gson.toJson(send);
		PrintStream ps;

		if (this.threadsMap.get(playerID) != null) {
			ps = new PrintStream(this.threadsMap.get(playerID).outputStream, true);
			ps.println(mensaje);

		}

	}

	public static UpdateOrCreateResponse createLobbyResponse(Map<String, ClientLobbyThread> usuarios,
			Map<String, Sala> salas) {
		List<User> listaUser = new ArrayList<User>();
		for (Map.Entry<String, ClientLobbyThread> entry : usuarios.entrySet()) {
			listaUser.add(new User(entry.getKey(), 0, 0));// Tendria que consultar en la base de datos sus
															// caracteristicas
		}
		List<ClientLobbySala> listaClientSalas = new ArrayList<ClientLobbySala>();
		for (Map.Entry<String, Sala> entry : salas.entrySet()) {
			listaClientSalas.add(new ClientLobbySala(entry.getKey(), entry.getValue().players.size(),
					entry.getValue().threadsMap.size(), entry.getValue().playing));// Tendria que consultar en la base
																					// de datos sus caracteristicas
		}
		return new UpdateOrCreateResponse(listaUser, listaClientSalas);
	}
	public String getPlayerID() {
		return this.playerID;
	}
}
