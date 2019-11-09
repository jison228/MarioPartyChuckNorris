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
import com.chucknorris.client.ClientNode;
import com.chucknorris.client.ClientPlayer;
import com.chucknorris.client.MovementResponsePrivate;
import com.chucknorris.client.MovementResponsePublic;
import com.chucknorris.game.Game;
import com.chucknorris.game.GameResponse;
import com.chucknorris.player.Player;
import com.google.gson.Gson;

public class ClientThread extends Thread {
	private InputStream inputStream = null;
	private OutputStream outputStream = null;

	private Socket clientSocket = null;
	private List<ClientThread> threads;
	Game juego;
	int diceResult = 0;
	
	public ClientThread(Socket clientSocket, List<ClientThread> threads, Game juego) {
		this.clientSocket = clientSocket;
		this.threads = threads;
		this.juego = juego;

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
				hola = hola + sc.next();
				Gson gson = new Gson();
				Command brigadaB = gson.fromJson(hola, Command.class);
				// MARIO SANTOS, LOGISTICA Y PLANIFICACION
				switch (brigadaB.getCommandName()) {
				case "MovementResponsePublic":
					// PlayResponse play = new Gson().fromJson(brigadaA.getCommandJSON(),);
					break;
				case "BifurcationResponse":
					Player currentPlayer1 = juego.getPlayerList().get(juego.getCurrentTurn());
					BifurcationResponse decision = gson.fromJson(brigadaB.getCommandJSON(), BifurcationResponse.class);
					GameResponse respuesta1 = juego.resolveIntersection(currentPlayer1, currentPlayer1.getNodeLocation().nextNodes().get(decision.decision), decision.movementsLeft);
					List<Player> listaPlayers = juego.getPlayerList();
					List<ClientPlayer> listaClientPlayers = new ArrayList<ClientPlayer>();
					for (int i = 0; i < listaPlayers.size(); i++) {
						Player playerToClient = listaPlayers.get(i);
						ClientPlayer clientToList = new ClientPlayer(playerToClient);
						listaClientPlayers.add(clientToList);
					}
					respuestaPublica = new MovementResponsePublic(diceResult, respuesta1.playerId,
							respuesta1.nodePath, listaClientPlayers);
					List<ClientNode> options = new ArrayList<ClientNode>();
					if (respuesta1.movementsLeft != 0) {
						for (int i = 0; i < currentPlayer1.getNodeLocation().nextNodes().size(); i++) {
							options.add(new ClientNode(currentPlayer1.getNodeLocation().nextNodes().get(i)));
						}
					} else {
						options = null;
					}

					respuestaPrivada = new MovementResponsePrivate(diceResult, respuesta1.playerId,
							respuesta1.nodePath, listaClientPlayers, options, respuesta1.compraDolares,respuesta1.movementsLeft);
					String paUno = gson.toJson(respuestaPrivada);
					Command dibujePriv = new Command("MovementResponsePrivate", paUno);
					
					String paTodos = gson.toJson(respuestaPublica);
					Command dibujePubli = new Command("MovementResponsePublic",paTodos);
					
					int socketToSend = juego.getCurrentTurn()%4;
					send(dibujePriv,socketToSend);
					for(int i=0;i<threads.size();i++) {
						if(i!=socketToSend) {
							threads.get(i).send(dibujePubli, i);
						}
					}
					break;
				case "TirarDado":
					Player currentPlayer = juego.getPlayerList().get(juego.getCurrentTurn());
					GameResponse respuesta = juego.play(currentPlayer);
					diceResult = respuesta.diceResult;
					List<Player> listaPlayers1 = juego.getPlayerList();
					List<ClientPlayer> listaClientPlayers1 = new ArrayList<ClientPlayer>();
					for (int i = 0; i < listaPlayers1.size(); i++) {
						Player playerToClient = listaPlayers1.get(i);
						ClientPlayer clientToList = new ClientPlayer(playerToClient);
						listaClientPlayers1.add(clientToList);
					}
					respuestaPublica = new MovementResponsePublic(respuesta.diceResult, respuesta.playerId,
							respuesta.nodePath, listaClientPlayers1);
					List<ClientNode> options1 = new ArrayList<ClientNode>();
					if (respuesta.movementsLeft != 0) {
						for (int i = 0; i < currentPlayer.getNodeLocation().nextNodes().size(); i++) {
							options1.add(new ClientNode(currentPlayer.getNodeLocation().nextNodes().get(i)));
						}
					} else {
						options = null;
					}

					respuestaPrivada = new MovementResponsePrivate(respuesta.diceResult, respuesta.playerId,
							respuesta.nodePath, listaClientPlayers1, options1, respuesta.compraDolares,respuesta.movementsLeft);
					String paUno1 = gson.toJson(respuestaPrivada);
					Command dibujePriv1 = new Command("MovementResponsePrivate", paUno1);
					
					String paTodos1 = gson.toJson(respuestaPublica);
					Command dibujePubli1 = new Command("MovementResponsePublic",paTodos1);
					
					int socketToSend1 = juego.getCurrentTurn()%4;
					send(dibujePriv1,socketToSend1);
					for(int i=0;i<threads.size();i++) {
						if(i!=socketToSend1) {
							threads.get(i).send(dibujePubli1, i);
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
		String mensaje = new Gson().toJson(send);
		PrintStream ps;

		synchronized (this) {

			if (this.threads.get(socket) != null) {
				ps = new PrintStream(this.threads.get(socket).outputStream, true);
				ps.println(mensaje);
			}

		}
	}

}
