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
				case "JoinSala": //Añadir GSON
					int unirse = Integer.valueOf(brigadaB.getCommandJSON());
					this.salas.get(unirse).threadsMap.put(playerID, this);//sala recibida por commando
					if(this.salas.get(unirse).players.size()<4) {
						this.salas.get(unirse).players.add(playerID);
					}
					threadsMap.remove(playerID);
					//Avisarle a los demas que actualicen el lobby
					break;
				case "CreateSala":
					if(salas.size()<4) {
						Sala nuevaSala = new Sala();
						nuevaSala.players.add(playerID);
						nuevaSala.threadsMap.put(playerID, this);
						this.salas.add(nuevaSala);
						threadsMap.remove(playerID);
						//Avisarle a los demas que actualicen el lobby
						//if(salas.size())
					} else {
						//Avisarle que es un pelotudo
					}
					break;
				}
			}
			sc.close();
		} catch (Exception e) {
			threadsMap.remove(playerID, this);
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
