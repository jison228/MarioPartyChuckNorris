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

import com.chucknorris.Command;
import com.chucknorris.User;
import com.chucknorris.client.ClientInfoSalas;
import com.chucknorris.client.UpdateOrCreateResponse;
import com.google.gson.Gson;

public class Server {
	private static ServerSocket serverSocketLobby = null;
	private static Socket clientSocketLobby = null;
	private static final int portLobbyNumber = 22222;
	private static Gson gson;
	
	public static void main(String args[]) throws Exception {
		List<Sala> salas = new ArrayList<Sala>();
		gson = new Gson();
		try {
			serverSocketLobby = new ServerSocket(portLobbyNumber);
		} catch (IOException e) {
			System.out.println(e);
		}
		Map<String,ClientLobbyThread> threadsMap = new HashMap<String, ClientLobbyThread>();

		InputStream inputStream;
		int num;
		while(true) {
			try {
				System.out.println("Esperando nueva conexion");
				clientSocketLobby = serverSocketLobby.accept();
				System.out.println("Alguien intenta conectarse");
				inputStream = clientSocketLobby.getInputStream();
				Scanner sc = new Scanner(inputStream);
				num = inputStream.read(); //Hacer un case dependiendo si es iniciar sesion o registrar
					String hola = String.valueOf((char) num);
					hola = hola + sc.nextLine();
					//Validacion de personaje
					ClientLobbyThread newClient = new ClientLobbyThread(hola,clientSocketLobby, threadsMap, salas);
					//ClientLobbyThread tendria que consultar los datos de sus playerId para mandarlo a los frames
					System.out.println("Ese alguien era: " + hola);
					threadsMap.put(hola, newClient);
					newClient.start();
					
					String usersMessage = gson.toJson(createLobbyResponse(threadsMap, salas));
					newClient.send(new Command("OpenLobby", usersMessage), hola);
					for(Map.Entry<String,ClientLobbyThread> entry : threadsMap.entrySet()) {
						if(!entry.getKey().equals(hola)) {
							newClient.send(new Command("UpdateLobby", usersMessage), entry.getKey());
						}
					
				}
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		
	}
	
	public static UpdateOrCreateResponse createLobbyResponse(Map<String, ClientLobbyThread> usuarios, List<Sala> salas) {
		List<User> listaUser = new ArrayList<User>();
		for(Map.Entry<String,ClientLobbyThread> entry : usuarios.entrySet()) {
			listaUser.add(new User(entry.getKey()));//Tendria que consultar en la base de datos sus caracteristicas
		}
		return new UpdateOrCreateResponse(listaUser, salas);
	}
}
