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
import com.chucknorris.client.GameInformation;
import com.chucknorris.client.GameParametersResponse;
import com.chucknorris.client.lobby.UpdateOrCreateLobbyResponse;
import com.chucknorris.client.sala.ClientRealSala;
import com.chucknorris.game.Game;
import com.chucknorris.player.Cristina;
import com.chucknorris.player.DelCanio;
import com.chucknorris.player.Espert;
import com.chucknorris.player.Macri;
import com.chucknorris.player.Player;
import com.google.gson.Gson;

public class ClientLobbyThread extends Thread {
	private Gson gson;
	private InputStream inputStream = null;
	private OutputStream outputStream = null;

	private Socket clientSocket = null;
	private String playerID;
	private String salaActual;
	private Map<String, ClientLobbyThread> threadsMap;
	private Map<String, Sala> salas;

	public ClientLobbyThread(String playerID, Socket clientSocket, Map<String, ClientLobbyThread> threadsMap,
			Map<String, Sala> salas) {
		this.clientSocket = clientSocket;
		this.threadsMap = threadsMap;
		this.playerID = playerID;
		salaActual = null;
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
					this.salas.get(this.salaActual).players.remove(playerID);
					usersMessage = gson.toJson(createSalaResponse(this.salas.get(this.salaActual)));
					for (Map.Entry<String, ClientLobbyThread> entry : this.salas.get(this.salaActual).threadsMap
							.entrySet()) {
						this.sendSala(new Command("UpdateSala", usersMessage), this.salaActual, entry.getKey());
					}
					break;
				case "SwitchFromSpectatorToPlayer":
					this.salas.get(this.salaActual).players.add(playerID);
					usersMessage = gson.toJson(createSalaResponse(this.salas.get(this.salaActual)));
					for (Map.Entry<String, ClientLobbyThread> entry : this.salas.get(this.salaActual).threadsMap
							.entrySet()) {
						this.sendSala(new Command("UpdateSala", usersMessage), this.salaActual, entry.getKey());
					}
					break;
				case "JoinSala":
					if (this.salas.get(brigadaB.getCommandJSON()).players.size() < 4)
						this.salas.get(brigadaB.getCommandJSON()).players.add(playerID);
					this.salas.get(brigadaB.getCommandJSON()).threadsMap.put(playerID, this);
					this.salaActual = brigadaB.getCommandJSON();
					threadsMap.remove(playerID);
					usersMessage = gson.toJson(createLobbyResponse(threadsMap, salas));
					for (Map.Entry<String, ClientLobbyThread> entry : threadsMap.entrySet()) {
						this.send(new Command("UpdateLobby", usersMessage), entry.getKey());
					}
					usersMessage = gson.toJson(createSalaResponse(this.salas.get(brigadaB.getCommandJSON())));
					this.sendSala(new Command("OpenSala", usersMessage), brigadaB.getCommandJSON(), playerID);
					for (Map.Entry<String, ClientLobbyThread> entry : this.salas
							.get(brigadaB.getCommandJSON()).threadsMap.entrySet()) {
						if (entry.getKey() != playerID) {
							this.sendSala(new Command("UpdateSala", usersMessage), brigadaB.getCommandJSON(),
									entry.getKey());
						}
					}
					break;
				case "CreateSala":
					if (salas.size() < 4) {
						Sala nuevaSala = new Sala(brigadaB.getCommandJSON());
						nuevaSala.players.add(playerID);
						nuevaSala.threadsMap.put(playerID, this);
						this.salas.put(brigadaB.getCommandJSON(), nuevaSala);
						threadsMap.remove(playerID);
						usersMessage = gson.toJson(createLobbyResponse(threadsMap, salas));
						this.salaActual = brigadaB.getCommandJSON();
						for (Map.Entry<String, ClientLobbyThread> entry : threadsMap.entrySet()) {
							this.send(new Command("UpdateLobby", usersMessage), entry.getKey());
						}
						usersMessage = gson.toJson(createSalaResponse(nuevaSala));
						this.sendSala(new Command("OpenSala", usersMessage), brigadaB.getCommandJSON(), playerID);
					} else {
						// Avisarle que se llego la capacidad maxima de salas
					}
					break;
				case "LeaveSala":
					threadsMap.put(playerID, this);
					this.salas.get(this.salaActual).threadsMap.remove(playerID);
					this.salas.get(this.salaActual).players.remove(playerID);
					if (this.salas.get(this.salaActual).threadsMap.isEmpty()) {
						this.salas.remove(this.salaActual);
					} else {
						usersMessage = gson.toJson(createSalaResponse(this.salas.get(this.salaActual)));
						for (Map.Entry<String, ClientLobbyThread> entry : this.salas.get(this.salaActual).threadsMap
								.entrySet()) {
							this.sendSala(new Command("UpdateSala", usersMessage), this.salaActual, entry.getKey());
						}
					}
					this.threadsMap.put(this.playerID, this);
					usersMessage = gson.toJson(createLobbyResponse(threadsMap, salas));
					this.send(new Command("LeaveSala", usersMessage), this.playerID);
					for (Map.Entry<String, ClientLobbyThread> entry : threadsMap.entrySet()) {
						if (!entry.getKey().equals(this.playerID)) {
							this.send(new Command("UpdateLobby", usersMessage), entry.getKey());
						}
					}
					break;
				case "ChatLobby":
					ChatResponse respuesta = new ChatResponse(playerID, brigadaB.getCommandJSON());
					for (Map.Entry<String, ClientLobbyThread> entry : threadsMap.entrySet()) {
						this.send(new Command("ChatLobby", gson.toJson(respuesta)), entry.getKey());
					}
					break;
				case "SalaChat":
					ChatResponse respuesta1 = new ChatResponse(playerID, brigadaB.getCommandJSON());
					for (Map.Entry<String, ClientLobbyThread> entry : this.salas.get(this.salaActual).threadsMap
							.entrySet()) {
						this.sendSala(new Command("SalaChat", gson.toJson(respuesta1)), this.salaActual,
								entry.getKey());
					}
					break;
				case "StartGame":
					GameParametersResponse parametros = gson.fromJson(brigadaB.getCommandJSON(),
							GameParametersResponse.class);
					List<Player> jugadores = new ArrayList<Player>();
					jugadores.add(new Espert(this.salas.get(this.salaActual).players.get(0), parametros.pesosIniciales,
							parametros.dolaresIniciales, parametros.salarioInicial));
					jugadores.add(new Cristina(this.salas.get(this.salaActual).players.get(1),
							parametros.pesosIniciales, parametros.dolaresIniciales, parametros.salarioInicial));
					jugadores.add(new Macri(this.salas.get(this.salaActual).players.get(2), parametros.pesosIniciales,
							parametros.dolaresIniciales, parametros.salarioInicial));
					jugadores.add(new DelCanio(this.salas.get(this.salaActual).players.get(3),
							parametros.pesosIniciales, parametros.dolaresIniciales, parametros.salarioInicial));
					this.salas.get(this.salaActual).juego = new Game(jugadores, parametros.mapName, parametros.diceMin,
							parametros.diceMax, parametros.precioDolar);

					GameInformation infoJuego = new GameInformation(parametros.mapName, jugadores,
							this.salas.get(this.salaActual).juego.getGameMap(), parametros.precioDolar);

					this.salas.get(this.salaActual).playing = true;
					usersMessage = gson.toJson(createLobbyResponse(threadsMap, salas));
					this.send(new Command("LeaveSala", usersMessage), this.playerID);
					for (Map.Entry<String, ClientLobbyThread> entry : threadsMap.entrySet()) {
						if (!entry.getKey().equals(this.playerID)) {
							this.send(new Command("UpdateLobby", usersMessage), entry.getKey());
						}
					}
					
					for (Map.Entry<String, ClientLobbyThread> entry : this.salas.get(this.salaActual).threadsMap
							.entrySet()) {
						this.sendSala(new Command("StartGame", gson.toJson(infoJuego)), this.salaActual,
								entry.getKey());
					}
					Thread.sleep(2000);
					this.sendSala(new Command("TirarDado", ""), this.salaActual,
							this.salas.get(this.salaActual).players
									.get(this.salas.get(this.salaActual).juego.getCurrentTurn()
											% this.salas.get(this.salaActual).players.size()));
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

	private void sendSala(Command send, String nameSala, String playerID) {
		String mensaje = gson.toJson(send);
		PrintStream ps;

		if (this.salas.get(nameSala).threadsMap.get(playerID) != null) {
			ps = new PrintStream(this.salas.get(nameSala).threadsMap.get(playerID).outputStream, true);
			ps.println(mensaje);

		}
	}

	public static UpdateOrCreateLobbyResponse createLobbyResponse(Map<String, ClientLobbyThread> usuarios,
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
		return new UpdateOrCreateLobbyResponse(listaUser, listaClientSalas);
	}

	public static ClientRealSala createSalaResponse(Sala salita) {
		List<User> listaPlayers = new ArrayList<User>();
		for (int i = 0; i < salita.players.size(); i++) {
			listaPlayers.add(new User(salita.players.get(i), 0, 0));
		}
		List<User> listaSpecs = new ArrayList<User>();
		for (Map.Entry<String, ClientLobbyThread> entry : salita.threadsMap.entrySet()) {
			boolean isPlayer = false;
			for (int i = 0; i < listaPlayers.size(); i++) {
				if (listaPlayers.get(i).getPlayerID().equals(entry.getKey())) {
					isPlayer = true;
				}
			}
			if (!isPlayer) {
				listaSpecs.add(new User(entry.getKey(), 0, 0));
			}
		}

		return new ClientRealSala(salita.name, listaPlayers, listaSpecs);
	}

	public String getPlayerID() {
		return this.playerID;
	}
}
