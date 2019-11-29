package com.chucknorris.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

import com.chucknorris.Command;
import com.chucknorris.User;
import com.chucknorris.client.ClientLobbySala;
import com.chucknorris.client.lobby.UpdateOrCreateLobbyResponse;
import com.google.gson.Gson;

public class Server {
	private static ServerSocket serverSocketLobby = null;
	private static Socket clientSocketLobby = null;
	private static final int portLobbyNumber = 22222;
	private static Gson gson;

	public static void main(String args[]) throws Exception {
		Map<String, Sala> salas = new HashMap<String, Sala>();
		gson = new Gson();
		try {
			serverSocketLobby = new ServerSocket(portLobbyNumber);
		} catch (IOException e) {
			System.out.println(e);
		}
		Map<String, ClientLobbyThread> threadsMap = new HashMap<String, ClientLobbyThread>();
		Map<String, Boolean> mapaDeVivos = null;
		Map<String, Integer> puntajes = null;
		Stack<String> posiciones = null;

		InputStream inputStream;
		int num;
		while (true) {
			try {
				System.out.println("Esperando nueva conexion");
				clientSocketLobby = serverSocketLobby.accept();
				System.out.println("Alguien intenta conectarse");
				inputStream = clientSocketLobby.getInputStream();
				Scanner sc = new Scanner(inputStream);
				num = inputStream.read(); // Hacer un case dependiendo si es iniciar sesion o registrar
				String hola = String.valueOf((char) num);
				hola = hola + sc.nextLine();
				// Validacion de personaje
				ClientLobbyThread newClient = new ClientLobbyThread(hola, clientSocketLobby, threadsMap, salas,
						posiciones, puntajes, mapaDeVivos);
				// ClientLobbyThread tendria que consultar los datos de sus playerId para
				// mandarlo a los frames
				System.out.println("Ese alguien era: " + hola);
				threadsMap.put(hola, newClient);
				newClient.start();

				String usersMessage = gson.toJson(createLobbyResponse(threadsMap, salas));
				newClient.send(new Command("OpenLobby", usersMessage), hola);
				for (Map.Entry<String, ClientLobbyThread> entry : threadsMap.entrySet()) {
					if (!entry.getKey().equals(hola)) {
						newClient.send(new Command("UpdateLobby", usersMessage), entry.getKey());
					}

				}
			} catch (IOException e) {
				System.out.println(e);
			}
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
}
