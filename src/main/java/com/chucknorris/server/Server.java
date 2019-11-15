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
import java.util.concurrent.Semaphore;

import com.chucknorris.Command;
import com.google.gson.Gson;

public class Server {
	private static ServerSocket serverSocketLobby = null;
	private static ServerSocket serverSocketMinigame=null;
	private static ServerSocket serverSocketGame=null;
	private static Socket clientSocketLobby = null;
	private static Socket clientSocketGame = null;
	private static Socket clientSocketMinigame = null;
	private static final int portLobbyNumber = 22222;
	private static final int portNumberGame = 22223;
	private static final int portNumberMinigame = 22224;
	static Semaphore semaphore2 = new Semaphore(4);
	
	public static void main(String args[]) throws Exception {
		List<Sala> salas = new ArrayList<Sala>();
		try {
			serverSocketLobby = new ServerSocket(portLobbyNumber);
			serverSocketGame = new ServerSocket(portNumberGame);
			serverSocketMinigame = new ServerSocket(portNumberMinigame);
		} catch (IOException e) {
			System.out.println(e);
		}
		Map<String,ClientLobbyThread> threadsMap = new HashMap<String, ClientLobbyThread>();
		
		Gson gson = new Gson();
		InputStream inputStream;
		int num;
		while(true) {
			try {
				System.out.println("Esperando nueva conexion");
				clientSocketLobby = serverSocketLobby.accept();
				System.out.println("Se conecto alguien");
				//Validacion de personaje
				inputStream = clientSocketLobby.getInputStream();
				Scanner sc = new Scanner(inputStream);
				while ((num = inputStream.read()) > 0) {
					String hola = String.valueOf((char) num);
					hola = hola + sc.nextLine();
					ClientLobbyThread newClient = new ClientLobbyThread(hola,clientSocketLobby, threadsMap, salas);
					threadsMap.put(hola, newClient);	
					newClient.start();
					newClient.send(new Command("OpenLobby", "OP"), hola);
				}
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		/*
		for (int i = 0; i < threads.size(); i++) {
			
			threads.get(i).start();
			threadsMinigame.get(i).start();
			threads.get(i).send(startGame, i);
		}
		Thread.sleep(2000);
		threads.get(0).send(habilitarBoton, 0);
		*/
	}
}
