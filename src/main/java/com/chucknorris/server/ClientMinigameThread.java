package com.chucknorris.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.Semaphore;

import com.chucknorris.Command;
import com.chucknorris.game.Game;

import com.google.gson.Gson;

public class ClientMinigameThread extends Thread {
	private InputStream inputStream = null;
	private OutputStream outputStreamMinigame = null;
	private Socket clientSocket = null;
	static Semaphore semaphoreDeMuertos = new Semaphore(1);
	private List<ClientMinigameThread> threads;
	Game juego;
	int diceResult = 0;
	Gson gson;
	PrintStream ps;
	private String character;
	private Stack<String> posiciones = new Stack<String>();
	private Map<String,Boolean> mapaDeVivos = new HashMap<String,Boolean>();
	private String playerId;
	private int espertScore;
	private int cristinaScore;
	private int macriScore;
	private int delCanoScore;
	private Map<String,Integer> puntajes = new HashMap<String,Integer>();

	public ClientMinigameThread(Socket clientSocket, List<ClientMinigameThread> threads2) {
		this.mapaDeVivos.put("Espert", true);
		this.mapaDeVivos.put("Cristina", true);
		this.mapaDeVivos.put("Macri", true);
		this.mapaDeVivos.put("Del Caño", true);
		gson = new Gson();
		this.clientSocket = clientSocket;
		this.threads = threads2;
		try {
			inputStream = this.clientSocket.getInputStream();
			outputStreamMinigame = this.clientSocket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void resolverPosicionConSemaforos(String playerId, String character,int puntaje) {
		posiciones.push(playerId);
		puntajes.put(playerId,puntaje);
		mapaDeVivos.remove(character);
	}
	public void run() {
		try {
			Scanner sc = new Scanner(inputStream);
			int num;
			while ((num = inputStream.read()) > 0) {
				String hola = String.valueOf((char) num);
				hola = hola + sc.next();

				Command brigadaB = gson.fromJson(hola, Command.class);
				// MARIO SANTOS, LOGISTICA Y PLANIFICACION
				switch (brigadaB.getCommandName()) {

				case "MandaleMecha":
					for (int i = 0; i < threads.size(); i++) {
						Command aSaltar = new Command("MandaleMecha", this.character);
						this.send(aSaltar, i);
					}
					break;
				case "JumpMinigame":
					switch (brigadaB.getCommandJSON()) {
					case "Espert":
						for (int i = 0; i < threads.size(); i++) {
							Command aSaltar = new Command("MinigameJumpEspert", "a");
							this.send(aSaltar, i);
						}
						break;
					case "Cristina":
						for (int i = 0; i < threads.size(); i++) {
							Command aSaltar = new Command("MinigameJumpCristina", "a");
							this.send(aSaltar, i);
						}
						break;
					case "Macri":
						for (int i = 0; i < threads.size(); i++) {
							Command aSaltar = new Command("MinigameJumpMacri", "a");
							this.send(aSaltar, i);
						}
						break;
					case "Del Caño":
						for (int i = 0; i < threads.size(); i++) {
							Command aSaltar = new Command("MinigameJumpDelCaño", "a");
							this.send(aSaltar, i);
						}
						break;
					}
					break;
				case "MeMoriSoyEspert":
					this.espertScore = Integer.parseInt(brigadaB.getCommandJSON());
					semaphoreDeMuertos.acquireUninterruptibly();
					this.resolverPosicionConSemaforos(this.playerId, "Espert",espertScore);
					for (int i = 0; i < threads.size(); i++) {
						Command aSaltar = new Command("MinigameMurioEspert", String.valueOf(mapaDeVivos.size()+1));
						this.send(aSaltar, i);
					}
					semaphoreDeMuertos.release();
					break;
				case "MeMoriSoyCristina":
					this.cristinaScore = Integer.parseInt(brigadaB.getCommandJSON());
					semaphoreDeMuertos.acquireUninterruptibly();
					this.resolverPosicionConSemaforos(this.playerId, "Cristina",cristinaScore);
					for (int i = 0; i < threads.size(); i++) {
						Command aSaltar = new Command("MinigameMurioCristina", String.valueOf(mapaDeVivos.size()+1));
						this.send(aSaltar, i);
					}
					semaphoreDeMuertos.release();
					break;
				case "MeMoriSoyMacri":
					this.macriScore = Integer.parseInt(brigadaB.getCommandJSON());
					semaphoreDeMuertos.acquireUninterruptibly();
					this.resolverPosicionConSemaforos(this.playerId, "Macri",macriScore);
					for (int i = 0; i < threads.size(); i++) {
						Command aSaltar = new Command("MinigameMurioMacri", String.valueOf(mapaDeVivos.size()+1));
						this.send(aSaltar, i);
					}
					semaphoreDeMuertos.release();
					break;
				case "MeMoriSoyDelCaño":
					this.delCanoScore = Integer.parseInt(brigadaB.getCommandJSON());
					semaphoreDeMuertos.acquireUninterruptibly();
					this.resolverPosicionConSemaforos(this.playerId, "Del Caño",delCanoScore);
					for (int i = 0; i < threads.size(); i++) {
						Command aSaltar = new Command("MinigameMurioDelCaño", String.valueOf(mapaDeVivos.size()+1));
						this.send(aSaltar, i);
					}
					semaphoreDeMuertos.release();
					break;
				}
				
			}
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(mapaDeVivos.isEmpty()) {
			//ACA SE TERMINA EL MINIJUEGO
			/*
			 * ya le envie las posiciones a todos los jugadores, y tengo sus 
			 */
		}
	}

	public void send(Command send, int socket) throws IOException {
		String mensaje = gson.toJson(send);

		synchronized (this) {

			if (this.threads.get(socket) != null) {
				ps = new PrintStream(this.threads.get(socket).outputStreamMinigame, true);
				ps.println(mensaje);
			}

		}
	}

}