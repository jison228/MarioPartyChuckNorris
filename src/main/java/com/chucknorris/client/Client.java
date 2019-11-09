package com.chucknorris.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.chucknorris.commons.Position;
import com.chucknorris.game.Game;
import com.chucknorris.gamemap.GameMap;
import com.chucknorris.gamemap.initiallizer.file.reader.csv.MapFileCSVReader;
import com.chucknorris.gamemap.nodes.Node;
import com.chucknorris.player.Cristina;
import com.chucknorris.player.DelCanio;
import com.chucknorris.player.Espert;
import com.chucknorris.player.Macri;
import com.chucknorris.player.Player;
import com.chucknorris.server.ClientThread;

public class Client {
	private static ServerSocket serverSocket = null;
	private static Socket clientSocket = null;
	private static final int maxClientsCount = 10;
	private static final ClientThread[] threads = new ClientThread[maxClientsCount];

	private static final int portNumber = 22222;

	public static void main(String args[]) throws Exception {

		try {
			serverSocket = new ServerSocket(portNumber);
		} catch (IOException e) {
			System.out.println(e);
		}

		try {
			InetAddress ip = InetAddress.getByName("192.168.43.102");
			Socket serverSocket = new Socket(ip,portNumber);
			Socket serverSocketMinigame = new Socket(ip,22223);
			ServerThread escuchador = new ServerThread(serverSocket,serverSocketMinigame);
			escuchador.start();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
