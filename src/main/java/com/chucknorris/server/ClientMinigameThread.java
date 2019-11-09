package com.chucknorris.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

import java.util.List;
import java.util.Scanner;

import com.chucknorris.Command;
import com.chucknorris.game.Game;

import com.google.gson.Gson;

public class ClientMinigameThread extends Thread {
	private InputStream inputStream = null;
	private OutputStream outputStreamMinigame = null;
	private Socket clientSocket = null;
	private List<ClientMinigameThread> threads;
	Game juego;
	int diceResult = 0;
	
	public ClientMinigameThread(Socket clientSocket, List<ClientMinigameThread> threads2) {
		this.clientSocket = clientSocket;
		this.threads=threads2;
		try {
			inputStream = this.clientSocket.getInputStream();
			outputStreamMinigame = this.clientSocket.getOutputStream();
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
				hola = hola + sc.next();
				Gson gson = new Gson();
				Command brigadaB = gson.fromJson(hola, Command.class);
				// MARIO SANTOS, LOGISTICA Y PLANIFICACION
				switch (brigadaB.getCommandName()) {
				
				case "MandaleMecha":
					for(int i=0;i<threads.size();i++) {
						Command aSaltar = new Command("MandaleMecha", "a");
							this.send(aSaltar, i);
						}
			
				break;
				case "JumpMinigame":
					switch( brigadaB.getCommandJSON()) {
					case "a":
						for(int i=0;i<threads.size();i++) {
							Command aSaltar = new Command("MinigameJumpA", "a");
								this.send(aSaltar, i);
							}
						break;
					case "b":
						for(int i=0;i<threads.size();i++) {
							Command aSaltar = new Command("MinigameJumpB", "b");
							this.send(aSaltar, i);
							}
						break;
					case "p":
						for(int i=0;i<threads.size();i++) {
							Command aSaltar = new Command("MinigameJumpP", "p");
							this.send(aSaltar, i);
							}
						break;
					case ".":
						for(int i=0;i<threads.size();i++) {
							Command aSaltar = new Command("MinigameJump.", ".");
							this.send(aSaltar, i);
							}
						break;
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
				ps = new PrintStream(this.threads.get(socket).outputStreamMinigame, true);
				ps.println(mensaje);
			}

		}
	}

}
