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
	private byte[] bytesName;
	private final ClientThread[] threads;
	private int maxClientsCount;
	Game juego;

	public ClientThread(Socket clientSocket, ClientThread[] threads, Game juego) {
		this.clientSocket = clientSocket;
		this.threads = threads;
		this.bytesName = bytesName;
		this.juego = juego;
		maxClientsCount = threads.length;

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
			while ((num = inputStream.read())>0) {
				String hola = String.valueOf((char)num);
				hola = hola + sc.next();
				Gson gson = new Gson();
				Command brigadaB = gson.fromJson(hola,Command.class);
				// MARIO SANTOS, LOGISTICA Y PLANIFICACION
				switch(brigadaB.getCommandName()) {
				case "MovementResponsePublic": 
					//PlayResponse play = new Gson().fromJson(brigadaA.getCommandJSON(),);
				break;
				case "TirarDado" :
					Player currentPlayer = juego.getPlayerList().get(juego.getCurrentTurn());
					GameResponse respuesta = juego.play(currentPlayer);
					MovementResponsePublic respuestaPublica;
					MovementResponsePrivate respuestaPrivada;
					List<Player> listaPlayers = juego.getPlayerList();
					List<ClientPlayer> listaClientPlayers = new ArrayList<ClientPlayer>();
					for(int i = 0;i < listaPlayers.size();i++) {
						Player playerToClient = listaPlayers.get(i);
						ClientPlayer clientToList = new ClientPlayer(playerToClient);
						listaClientPlayers.add(clientToList);
					}
					respuestaPublica = new MovementResponsePublic(respuesta.diceResult, respuesta.playerId, respuesta.nodePath, listaClientPlayers);
					List<ClientNode> options = new ArrayList<ClientNode>();
					if(respuesta.movementsLeft != 0) {
						for(int i=0;i < currentPlayer.getNodeLocation().nextNodes().size();i++) {
							options.add(new ClientNode(currentPlayer.getNodeLocation().nextNodes().get(i)));
						}
					} else {
						options = null;
					}
					 
					respuestaPrivada = new MovementResponsePrivate(respuesta.diceResult, respuesta.playerId, respuesta.nodePath, listaClientPlayers, options, respuesta.compraDolares);
					String patodos = gson.toJson(respuestaPublica);
					Command dibuje = new Command("MovementResponsePublic", patodos);
					send(dibuje);
					break;
				}
			}
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void send(Command send) throws IOException {
		String mensaje = new Gson().toJson(send);
		PrintStream ps;

		synchronized (this) {
			for (int i = 0; i < 1; i++) {
				if (this.threads[i] != null) {
					ps = new PrintStream(this.threads[i].outputStream, true); 
					ps.println(mensaje);
				}
			}
		}
	}

}
