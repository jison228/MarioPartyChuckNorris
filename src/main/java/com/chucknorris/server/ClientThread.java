package com.chucknorris.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.chucknorris.Command;
import com.chucknorris.client.ActualizarCompraResponse;
import com.chucknorris.client.ChatResponse;
import com.chucknorris.client.ClientNode;
import com.chucknorris.client.ClientPlayer;
import com.chucknorris.client.EndTurnResponse;
import com.chucknorris.client.MovementResponsePrivate;
import com.chucknorris.client.MovementResponsePublic;
import com.chucknorris.game.Game;
import com.chucknorris.game.GameResponse;
import com.chucknorris.player.Espert;
import com.chucknorris.player.Player;
import com.google.gson.Gson;

public class ClientThread extends Thread {
	private InputStream inputStream = null;
	private OutputStream outputStream = null;

	private Socket clientSocket = null;
	private List<ClientThread> threads;
	Game juego;
	int diceResult = 0;
	private Gson gson;

	public ClientThread(Socket clientSocket, List<ClientThread> threads, Game juego) {
		this.clientSocket = clientSocket;
		this.threads = threads;
		this.juego = juego;
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
			while ((num = inputStream.read()) > 0) {
				String hola = String.valueOf((char) num);
				hola = hola + sc.nextLine();
				Command brigadaB = gson.fromJson(hola, Command.class);
				// MARIO SANTOS, LOGISTICA Y PLANIFICACION
				switch (brigadaB.getCommandName()) {
				case "Chat":
					ChatResponse chatResponse = new ChatResponse("pepe", brigadaB.getCommandJSON());
					String chatGson = gson.toJson(chatResponse);
					Command chatCommand = new Command("Chat", chatGson);
					for (int i = 0; i < threads.size(); i++) {
						this.send(chatCommand, i);
					}
					break;
				case "Compra":
					Player compraCurrentPlayer = juego.getPlayerList().get(juego.getCurrentTurn() % 4);
					double pesos = Double.valueOf(brigadaB.getCommandJSON());
					compraCurrentPlayer.buyDolares(pesos, juego.getPrecioDolar());
					List<ClientPlayer> compraClientPlayers = new ArrayList<ClientPlayer>();
					for (int i = 0; i < juego.getPlayerList().size(); i++) {
						Player playerToClient = juego.getPlayerList().get(i);
						ClientPlayer clientToList = new ClientPlayer(playerToClient);
						compraClientPlayers.add(clientToList);
					}

					String compraGson = gson.toJson(new ActualizarCompraResponse(compraClientPlayers));
					Command compraCommand = new Command("Compra", compraGson);
					for (int i = 0; i < threads.size(); i++) {
						this.send(compraCommand, i);
					}
					break;
				case "EndTurn":
					boolean cfinish = false;
					juego.endTurn();

					Player ganador = new Espert(0, 0, 0);
					for (Player player : juego.getPlayerList()) {
						if (player.getDolares() >= ganador.getDolares()) {
							ganador = player;
						}
					}
					if (ganador.getDolares() > 200) {
						for (int i = 0; i < threads.size(); i++) {
							this.send(new Command("EndGame", ganador.getCharacter()), i);
							cfinish = true;
						}
					}

					Player endCurrentPlayer = juego.getPlayerList().get(juego.getCurrentTurn() % 4);

					EndTurnResponse endTurnResponse = new EndTurnResponse(juego.getCurrentTurn(),
							juego.getPrecioDolar(), endCurrentPlayer);
					String fin = gson.toJson(endTurnResponse);
					Command enviar = new Command("EndTurn", fin);
					for (int i = 0; i < threads.size(); i++) {
						this.send(enviar, i);
					}

					Command habilitarBoton = new Command("TirarDado", "");
					Thread.sleep(1000);
					this.send(habilitarBoton, juego.getCurrentTurn() % 4);

					Thread.sleep(1000);
					if (juego.getCurrentTurn() % 4 == 0) {
						juego.aumentarPrecioDolar();
						if (!cfinish) {
							Command minigameCommand = new Command("StartMinigame", "");
							for (int i = 0; i < threads.size(); i++) {
								this.send(minigameCommand, i);
							}
						}
					}

					break;
				case "BifurcationResponse":
					Player bifCurrentPlayer = juego.getPlayerList().get(juego.getCurrentTurn() % 4);
					BifurcationResponse bifResponse = gson.fromJson(brigadaB.getCommandJSON(),
							BifurcationResponse.class);
					GameResponse respuesta1 = juego.resolveIntersection(bifCurrentPlayer,
							bifCurrentPlayer.getNodeLocation().nextNodes().get(bifResponse.decision),
							bifResponse.movementsLeft);
					List<Player> bifListaPlayers = juego.getPlayerList();
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

					int socketToSend = juego.getCurrentTurn() % threads.size();
					this.send(privCommand, socketToSend);
					for (int i = 0; i < threads.size(); i++) {
						if (i != socketToSend) {
							this.send(publicCommand, i);
						}
					}
					break;
				case "TirarDado":
					Player diceCurrentPlayer = juego.getPlayerList().get(juego.getCurrentTurn() % 4);
					GameResponse gameResponse = juego.play(diceCurrentPlayer);
					diceResult = gameResponse.diceResult;
					List<Player> dicePlayers = juego.getPlayerList();
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

					int socketToSend2 = juego.getCurrentTurn() % 4;
					this.send(privCommand2, socketToSend2);
					for (int i = 0; i < threads.size(); i++) {
						if (i != socketToSend2) {
							this.send(publicCommand2, i);
						}
					}
					break;
				}
			}
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void send(Command send, int socket) throws IOException {
		String mensaje = gson.toJson(send);
		PrintStream ps;

		if (this.threads.get(socket) != null) {
			ps = new PrintStream(this.threads.get(socket).outputStream, true);
			ps.println(mensaje);

		}

	}

}
