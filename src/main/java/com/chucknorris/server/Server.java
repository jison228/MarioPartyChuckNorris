package com.chucknorris.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.chucknorris.Command;
import com.chucknorris.User;
import com.chucknorris.client.ClientLobbySala;
import com.chucknorris.client.lobby.UpdateOrCreateLobbyResponse;
import com.jwt.hibernate.Jugador;
import com.jwt.hibernate.JugadorDAO;
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
		List<String> mapaDeVivos = new ArrayList<String>();
		Map<String, Integer> puntajes = new HashMap<String, Integer>();
		PrintStream ps;

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
				NamePasswordResponse nyc = gson.fromJson(hola, NamePasswordResponse.class);
				// Validacion de personaje
				Jugador pepe = null;
				if(!nyc.reg)
					pepe = JugadorDAO.loguear(nyc.name, nyc.password);
				else {
					pepe = JugadorDAO.registrar(nyc.name, nyc.password);
					if(pepe==null) {
						ps = new PrintStream(clientSocketLobby.getOutputStream());
						ps.println(gson.toJson(new Command("FatalError", "Nombre ya registrado")));
						clientSocketLobby.close();
						continue;
					}
				}
				if(pepe==null) {
					ps = new PrintStream(clientSocketLobby.getOutputStream());
					ps.println(gson.toJson(new Command("FatalError", "Nombre o Contraseña Incorrectos")));
					clientSocketLobby.close();
					continue;
				}
				if(threadsMap.containsKey(nyc.name)) {
					ps = new PrintStream(clientSocketLobby.getOutputStream());
					ps.println(gson.toJson(new Command("FatalError", "Ya se encuentra conectado alguien con ese nombre")));
					clientSocketLobby.close();
					continue;
				}
				ClientLobbyThread newClient = new ClientLobbyThread(nyc.name, clientSocketLobby, threadsMap, salas,puntajes, mapaDeVivos);
				// ClientLobbyThread tendria que consultar los datos de sus playerId para
				// mandarlo a los frames
				System.out.println("Ese alguien era: " + nyc.name);
				threadsMap.put(nyc.name, newClient);
				newClient.start();

				String usersMessage = gson.toJson(createLobbyResponse(threadsMap, salas));
				newClient.send(new Command("OpenLobby", usersMessage), nyc.name);
				for (Map.Entry<String, ClientLobbyThread> entry : threadsMap.entrySet()) {
					if (!entry.getKey().equals(nyc.name)) {
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
					entry.getValue().threadsMap.size(), entry.getValue().playing, entry.getValue().priv));// Tendria que consultar en la base
																					// de datos sus caracteristicas
		}
		return new UpdateOrCreateLobbyResponse(listaUser, listaClientSalas);
	}
}
