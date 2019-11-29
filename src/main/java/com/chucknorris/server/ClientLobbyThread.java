package com.chucknorris.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.Semaphore;

import com.chucknorris.Command;
import com.chucknorris.User;
import com.chucknorris.client.ActualizarCompraResponse;
import com.chucknorris.client.ChatResponse;
import com.chucknorris.client.ClientLobbySala;
import com.chucknorris.client.ClientNode;
import com.chucknorris.client.ClientPlayer;
import com.chucknorris.client.EndTurnResponse;
import com.chucknorris.client.GameInformation;
import com.chucknorris.client.GameParametersResponse;
import com.chucknorris.client.MovementResponsePrivate;
import com.chucknorris.client.MovementResponsePublic;
import com.chucknorris.client.lobby.UpdateOrCreateLobbyResponse;
import com.chucknorris.client.sala.ClientRealSala;
import com.chucknorris.game.Game;
import com.chucknorris.game.GameResponse;
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
	private String character;
	private Map<String, ClientLobbyThread> threadsMap;
	private Map<String, Sala> salas;
	private int diceResult = 0;
	private List<ClientNode> bifOptions;
	
	private int espertScore;
	private int cristinaScore;
	private int macriScore;
	private int delCanoScore;
	private List<String> mapaDeVivos;
	private Map<String,Integer> puntajes;

	public ClientLobbyThread(String playerID, Socket clientSocket, Map<String, ClientLobbyThread> threadsMap,
			Map<String, Sala> salas, Map<String,Integer> puntajes, List<String> mapaDeVivos) {

		this.puntajes = puntajes;
		this.mapaDeVivos = mapaDeVivos;
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
			MovementResponsePublic respuestaPublica;
			MovementResponsePrivate respuestaPrivada;

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
					ChatResponse respuestaSalaChat = new ChatResponse(playerID, brigadaB.getCommandJSON());
					for (Map.Entry<String, ClientLobbyThread> entry : this.salas.get(this.salaActual).threadsMap
							.entrySet()) {
						this.sendSala(new Command("SalaChat", gson.toJson(respuestaSalaChat)), this.salaActual,
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
				case "BifurcationResponse":
					Player bifCurrentPlayer = this.salas.get(this.salaActual).juego.getPlayerList()
							.get(this.salas.get(this.salaActual).juego.getCurrentTurn()
									% this.salas.get(this.salaActual).players.size());
					BifurcationResponse bifResponse = gson.fromJson(brigadaB.getCommandJSON(),
							BifurcationResponse.class);
					GameResponse respuesta1 = this.salas.get(this.salaActual).juego.resolveIntersection(
							bifCurrentPlayer, bifCurrentPlayer.getNodeLocation().nextNodes().get(bifResponse.decision),
							bifResponse.movementsLeft);
					List<Player> bifListaPlayers = this.salas.get(this.salaActual).juego.getPlayerList();
					List<ClientPlayer> bifClientPlayers = new ArrayList<ClientPlayer>();
					for (int i = 0; i < bifListaPlayers.size(); i++) {
						Player playerToClient = bifListaPlayers.get(i);
						ClientPlayer clientToList = new ClientPlayer(playerToClient);
						bifClientPlayers.add(clientToList);
					}
					respuestaPublica = new MovementResponsePublic(diceResult, respuesta1.playerId, respuesta1.nodePath,
							bifClientPlayers);
					List<ClientNode> bifOptions = new ArrayList<ClientNode>();
					if (respuesta1.movementsLeft != 0) {
						for (int i = 0; i < bifCurrentPlayer.getNodeLocation().nextNodes().size(); i++) {
							bifOptions.add(new ClientNode(bifCurrentPlayer.getNodeLocation().nextNodes().get(i)));
						}
					} else {
						bifOptions = null;
					}

					respuestaPrivada = new MovementResponsePrivate(diceResult, respuesta1.playerId, respuesta1.nodePath,
							bifClientPlayers, bifOptions, respuesta1.compraDolares, respuesta1.movementsLeft);
					String privGson = gson.toJson(respuestaPrivada);
					Command privCommand = new Command("MovementResponsePrivate", privGson);

					String publicGson = gson.toJson(respuestaPublica);
					Command publicCommand = new Command("MovementResponsePublic", publicGson);

					this.sendSala(privCommand, this.salaActual, this.playerID);

					for (Map.Entry<String, ClientLobbyThread> entry : this.salas.get(this.salaActual).threadsMap
							.entrySet()) {
						if (entry.getKey() != this.playerID) {
							this.sendSala(publicCommand, this.salaActual, entry.getKey());
						}
					}
					break;
				case "TirarDado":
					Player diceCurrentPlayer = this.salas.get(this.salaActual).juego.getPlayerList()
							.get(this.salas.get(this.salaActual).juego.getCurrentTurn() % 4); // asdsad//
					GameResponse gameResponse = this.salas.get(this.salaActual).juego.play(diceCurrentPlayer);
					this.diceResult = gameResponse.diceResult;
					List<Player> dicePlayers = this.salas.get(this.salaActual).juego.getPlayerList();
					List<ClientPlayer> diceClientPlayers = new ArrayList<ClientPlayer>();
					for (int i = 0; i < dicePlayers.size(); i++) {
						Player playerToClient = dicePlayers.get(i);
						ClientPlayer clientToList = new ClientPlayer(playerToClient);
						diceClientPlayers.add(clientToList);
					}
					respuestaPublica = new MovementResponsePublic(gameResponse.diceResult, gameResponse.playerId,
							gameResponse.nodePath, diceClientPlayers);
					List<ClientNode> diceOptions = new ArrayList<ClientNode>();
					if (gameResponse.movementsLeft != 0) {
						for (int i = 0; i < diceCurrentPlayer.getNodeLocation().nextNodes().size(); i++) {
							diceOptions.add(new ClientNode(diceCurrentPlayer.getNodeLocation().nextNodes().get(i)));
						}
					} else {
						bifOptions = null;
					}

					respuestaPrivada = new MovementResponsePrivate(gameResponse.diceResult, gameResponse.playerId,
							gameResponse.nodePath, diceClientPlayers, diceOptions, gameResponse.compraDolares,
							gameResponse.movementsLeft);
					String privGson2 = gson.toJson(respuestaPrivada);
					Command privCommand2 = new Command("MovementResponsePrivate", privGson2);

					String publicGson2 = gson.toJson(respuestaPublica);
					Command publicCommand2 = new Command("MovementResponsePublic", publicGson2);

					this.sendSala(privCommand2, this.salaActual, this.playerID);

					for (Map.Entry<String, ClientLobbyThread> entry : this.salas.get(this.salaActual).threadsMap
							.entrySet()) {
						if (entry.getKey() != this.playerID) {
							this.sendSala(publicCommand2, this.salaActual, entry.getKey());
						}
					}
					break;
				case "EndTurn":
					for(int i = 0; i < this.salas.get(this.salaActual).juego.getPlayerList().size(); i++) {
						if(this.salas.get(this.salaActual).juego.getPlayerList().get(i).getPlayerID().equals(this.playerID)) {
							this.character = this.salas.get(this.salaActual).juego.getPlayerList().get(i).getCharacter();
						}
					}
					boolean cfinish = false;
					this.salas.get(this.salaActual).juego.endTurn();

					Player ganador = new Espert(0, 0, 0);
					for (Player player : this.salas.get(this.salaActual).juego.getPlayerList()) {
						if (player.getDolares() >= ganador.getDolares()) {
							ganador = player;
						}
					}
					if (ganador.getDolares() > 200) {
						for (Map.Entry<String, ClientLobbyThread> entry : this.salas.get(this.salaActual).threadsMap
								.entrySet()) {
							this.sendSala(new Command("EndGame", ganador.getCharacter()), this.salaActual,
									entry.getKey());

						}
						cfinish = true;
					}

					Player endCurrentPlayer = this.salas.get(this.salaActual).juego.getPlayerList()
							.get(this.salas.get(this.salaActual).juego.getCurrentTurn() % 4);

					EndTurnResponse endTurnResponse = new EndTurnResponse(
							this.salas.get(this.salaActual).juego.getCurrentTurn(),
							this.salas.get(this.salaActual).juego.getPrecioDolar(), endCurrentPlayer);
					String fin = gson.toJson(endTurnResponse);
					Command enviar = new Command("EndTurn", fin);

					for (Map.Entry<String, ClientLobbyThread> entry : this.salas.get(this.salaActual).threadsMap
							.entrySet()) {
						this.sendSala(enviar, this.salaActual, entry.getKey());

					}

					Thread.sleep(1000);
					this.sendSala(new Command("TirarDado", ""), this.salaActual,
							this.salas.get(this.salaActual).players
									.get(this.salas.get(this.salaActual).juego.getCurrentTurn()
											% this.salas.get(this.salaActual).players.size()));

					Thread.sleep(1000);
					if (this.salas.get(this.salaActual).juego.getCurrentTurn() % 4 == 0) {
						this.salas.get(this.salaActual).juego.aumentarPrecioDolar();
						if (!cfinish) {
							this.mapaDeVivos = new ArrayList<String>();
							this.mapaDeVivos.add("Espert");
							this.mapaDeVivos.add("Cristina");
							this.mapaDeVivos.add("Macri");
							this.mapaDeVivos.add("Del Caño");
							puntajes = new HashMap<String,Integer>();

							
							Command minigameCommand = new Command("StartMinigame", "");
							for (Map.Entry<String, ClientLobbyThread> entry : this.salas.get(this.salaActual).threadsMap
									.entrySet()) {
								this.sendSala(minigameCommand, this.salaActual, entry.getKey());
							}
						}
					}

					break;
				case "Compra":
					Player compraCurrentPlayer = this.salas.get(this.salaActual).juego.getPlayerList()
							.get(this.salas.get(this.salaActual).juego.getCurrentTurn() % 4);
					double pesos = Double.valueOf(brigadaB.getCommandJSON());
					compraCurrentPlayer.buyDolares(pesos, this.salas.get(this.salaActual).juego.getPrecioDolar());
					List<ClientPlayer> compraClientPlayers = new ArrayList<ClientPlayer>();
					for (int i = 0; i < this.salas.get(this.salaActual).juego.getPlayerList().size(); i++) {
						Player playerToClient = this.salas.get(this.salaActual).juego.getPlayerList().get(i);
						ClientPlayer clientToList = new ClientPlayer(playerToClient);
						compraClientPlayers.add(clientToList);
					}

					String compraGson = gson.toJson(new ActualizarCompraResponse(compraClientPlayers));
					Command compraCommand = new Command("Compra", compraGson);
					for (Map.Entry<String, ClientLobbyThread> entry : this.salas.get(this.salaActual).threadsMap
							.entrySet()) {
						this.sendSala(compraCommand, this.salaActual, entry.getKey());

					}
					break;
				case "GameChat":
					ChatResponse respuestaGameChat = new ChatResponse(playerID, brigadaB.getCommandJSON());
					for (Map.Entry<String, ClientLobbyThread> entry : this.salas.get(this.salaActual).threadsMap
							.entrySet()) {
						this.sendSala(new Command("GameChat", gson.toJson(respuestaGameChat)), this.salaActual,
								entry.getKey());
					}
					break;
				case "MandaleMecha":
					for (Map.Entry<String, ClientLobbyThread> entry : this.salas.get(this.salaActual).threadsMap
							.entrySet()) {
						this.sendSala(new Command("MandaleMecha", entry.getValue().character), this.salaActual,
								entry.getKey());
					}
				break;
				case "JumpMinigame":
					for (Map.Entry<String, ClientLobbyThread> entry : this.salas.get(this.salaActual).threadsMap
							.entrySet()) {
						this.sendSala(new Command("MinigameJump" + brigadaB.getCommandJSON(), "a"), this.salaActual,
								entry.getKey());
					}
					break;
				case "MeMoriSoyEspert":
					this.espertScore = Integer.parseInt(brigadaB.getCommandJSON());

					for (Map.Entry<String, ClientLobbyThread> entry : this.salas.get(this.salaActual).threadsMap
							.entrySet()) {
						this.sendSala(new Command("MinigameMurioEspert","1"), this.salaActual,
								entry.getKey());
					}
					this.resolverPosicionConSemaforos("Espert",espertScore);
					break;
				case "MeMoriSoyCristina":
					this.cristinaScore = Integer.parseInt(brigadaB.getCommandJSON());

					for (Map.Entry<String, ClientLobbyThread> entry : this.salas.get(this.salaActual).threadsMap
							.entrySet()) {
						this.sendSala(new Command("MinigameMurioCristina", "2"), this.salaActual,
								entry.getKey());
					}
					this.resolverPosicionConSemaforos("Cristina",cristinaScore);
					break;
				case "MeMoriSoyMacri":
					this.macriScore = Integer.parseInt(brigadaB.getCommandJSON());

					for (Map.Entry<String, ClientLobbyThread> entry : this.salas.get(this.salaActual).threadsMap
							.entrySet()) {
						this.sendSala(new Command("MinigameMurioMacri","3"), this.salaActual,
								entry.getKey());
					}
					this.resolverPosicionConSemaforos("Macri",macriScore);
					break;
				case "MeMoriSoyDelCaño":
					this.delCanoScore = Integer.parseInt(brigadaB.getCommandJSON());
					for (Map.Entry<String, ClientLobbyThread> entry : this.salas.get(this.salaActual).threadsMap
							.entrySet()) {
						this.sendSala(new Command("MinigameMurioDelCaño", "4"), this.salaActual,
								entry.getKey());
					}
					this.resolverPosicionConSemaforos("Del Caño",delCanoScore);
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
	
	public void resolverPosicionConSemaforos(String character,int puntaje) {
		mapaDeVivos.remove(character);
		String playerMuerto = null;
		for(int i = 0; i < this.salas.get(this.salaActual).juego.getPlayerList().size(); i++) {
			if(this.salas.get(this.salaActual).juego.getPlayerList().get(i).getCharacter().equals(character)) {
				playerMuerto = this.salas.get(this.salaActual).juego.getPlayerList().get(i).getPlayerID();
			}
		}
		puntajes.put(playerMuerto,puntaje);
		
		if(mapaDeVivos.isEmpty()) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (Map.Entry<String, ClientLobbyThread> entry : this.salas.get(this.salaActual).threadsMap
					.entrySet()) {
				this.sendSala(new Command("FinishMiniGame", " "), this.salaActual,
						entry.getKey());
			}
		}

	}
}
