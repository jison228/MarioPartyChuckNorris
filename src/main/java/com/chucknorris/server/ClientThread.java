package com.chucknorris.server;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

import com.chucknorris.Command;
import com.chucknorris.client.MovementResponsePublic;
import com.chucknorris.game.Game;
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
					juego.play(juego.getPlayerList().get(juego.getCurrentTurn()));
				break;
				}
			}
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void send(byte[] b) throws IOException {
		synchronized (this) {
			for (int i = 0; i < this.maxClientsCount; i++) {
				if (this.threads[i] != null) {
					this.threads[i].outputStream.write(b);
					this.threads[i].outputStream.flush();
				}
			}
		}
	}

}
