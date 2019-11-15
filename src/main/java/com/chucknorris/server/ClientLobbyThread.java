package com.chucknorris.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;

import com.chucknorris.Command;
import com.google.gson.Gson;

public class ClientLobbyThread extends Thread{
	private Gson gson;
	private InputStream inputStream = null;
	private OutputStream outputStream = null;

	private Socket clientSocket = null;
	private String playerID;
	private Map<String,ClientLobbyThread> threadsMap;
	
	public ClientLobbyThread(String playerID,Socket clientSocket, Map<String,ClientLobbyThread> threadsMap) {
		this.clientSocket = clientSocket;
		this.threadsMap = threadsMap;
		this.playerID = playerID;
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
				
			}
			sc.close();
		} catch (Exception e) {
			threadsMap.remove(playerID, this);
		}

	}
}
