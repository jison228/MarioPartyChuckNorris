package com.chucknorris.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.chucknorris.Command;
import com.google.gson.Gson;

public class ClientLobbyThread extends Thread {
	private Gson gson;
	private InputStream inputStream = null;
	private OutputStream outputStream = null;

	private Socket clientSocket = null;
	private String playerID;
	private Map<String, ClientLobbyThread> threadsMap;
	private List<Sala> salas;

	public ClientLobbyThread(String playerID, Socket clientSocket, Map<String, ClientLobbyThread> threadsMap,
			List<Sala> salas) {
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

			while ((num = inputStream.read()) > 0) {
				String hola = String.valueOf((char) num);
				hola = hola + sc.nextLine();
				Command brigadaB = gson.fromJson(hola, Command.class);
				switch (brigadaB.getCommandName()) {
				case "SwitchFromPlayerToSpectator":
					int switchPS = Integer.valueOf(brigadaB.getCommandJSON());
					this.salas.get(switchPS).players.remove(playerID);
					//Avisarle a los demas que actualicen la sala
				case "SwitchFromSpectatorToPlayer":
					int switchSP = Integer.valueOf(brigadaB.getCommandJSON());
					this.salas.get(switchSP).players.add(playerID);
					//Avisarle a los demas que actualicen la sala
				case "JoinSalaAsPlayer": //Añadir GSON
					int unirseP = Integer.valueOf(brigadaB.getCommandJSON());
					this.salas.get(unirseP).threadsMap.put(playerID, this);//sala recibida por commando
					if(this.salas.get(unirseP).players.size()<4) {
						this.salas.get(unirseP).players.add(playerID);
					}
					threadsMap.remove(playerID);
					//Avisarle a los demas que actualicen el lobby
					//Mandarle al jugador la info sobre la sala
					//Avisarle a los de la sala que actualicen
					break;
				case "JoinSalaAsSpectator":
					int unirseS = Integer.valueOf(brigadaB.getCommandJSON());
					this.salas.get(unirseS).threadsMap.put(playerID, this);//sala recibida por commando
					//Avisarle a los demas que actualicen el lobby
					//Mandarle al jugador la info sobre la sala
					//Avisarle a los de la sala que actualicen
					break;
				case "CreateSala":
					if(salas.size()<4) {
						Sala nuevaSala = new Sala();
						nuevaSala.players.add(playerID);
						nuevaSala.threadsMap.put(playerID, this);
						this.salas.add(nuevaSala);
						threadsMap.remove(playerID);
						//Avisarle a los del lobby que actualicen
						//Enviarle al player la info de la sala
					} else {
						//Avisarle que es un pelotudo
					}
					break;
				case "LeaveSala":
					threadsMap.put(playerID,this);
					int salir = Integer.valueOf(brigadaB.getCommandJSON());
					salas.get(salir).threadsMap.remove(playerID);
					salas.get(salir).players.remove(playerID);
					//Avisarle a los de la sala que actualicen
					//Avisarles a los del lobby que actualicen
					break;
				//Pensar caso de exit Lobby
				}
			}
			sc.close();
		} catch (Exception e) {
			if(threadsMap.remove(playerID)==null) {
				for(int i = 0; i < salas.size();i++) {
					if(salas.get(i).players.remove(playerID)) {
						//Avisar a los de la sala que actualicen
					}
				}
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
}
