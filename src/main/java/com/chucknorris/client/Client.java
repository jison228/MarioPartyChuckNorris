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

		GameMap mapa1;
		MapFileCSVReader mapFileCSVReader = new MapFileCSVReader("newMap1.txt");
		mapa1 = mapFileCSVReader.buildGameMap();
		Node ini = mapa1.getMap().get(new Position(0, 0));
		Espert p1 = new Espert(1450, 150, 100);
		Cristina p2 = new Cristina(150, 100, 900);
		Macri p3 = new Macri(500, 100, 100);
		DelCanio p4 = new DelCanio(150, 100, 100);
		p1.setNodeLocation(ini);
		p2.setNodeLocation(ini);
		p3.setNodeLocation(ini);
		p4.setNodeLocation(ini);
		List<Player> listaP = new ArrayList<Player>();
		listaP.add(p1);
		listaP.add(p2);
		listaP.add(p3);
		listaP.add(p4);
		Game juego01 = new Game(listaP, mapa1,20);
		// me conecto al server
		try {
			InetAddress ip = InetAddress.getByName("localhost");
			Socket serverSocket = new Socket(ip, portNumber);
			ServerThread escuchador = new ServerThread(serverSocket, juego01);
			escuchador.start();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
