package com.chucknorris.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.chucknorris.Command;
import com.chucknorris.client.GameInformation;
import com.chucknorris.commons.Dice;
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
import com.google.gson.Gson;

public class Server {
	private static ServerSocket serverSocket = null;
	private static Socket clientSocket = null;
	private static final int portNumber = 22222;

	public static void main(String args[]) throws Exception {

		try {
			serverSocket = new ServerSocket(portNumber);
		} catch (IOException e) {
			System.out.println(e);
		}
		List<ClientThread> threads = new ArrayList<ClientThread>();
		
		GameMap mapa1;
		MapFileCSVReader mapFileCSVReader = new MapFileCSVReader("newMap1.txt");
		mapa1 = mapFileCSVReader.buildGameMap();
		Node ini = mapa1.getMap().get(new Position(0,0));
		Espert p1 = new Espert(100, 50, 300);
		Cristina p2 = new Cristina(100, 50, 300);
		Macri p3 = new Macri(100, 50, 300);
		DelCanio p4 = new DelCanio(100, 50, 300);
		p1.setNodeLocation(ini);
		p2.setNodeLocation(ini);
		p3.setNodeLocation(ini);
		p4.setNodeLocation(ini);
		List<Player> listaP = new ArrayList<Player>();
		listaP.add(p1);
		listaP.add(p2);
		listaP.add(p3);
		listaP.add(p4);
		Game juego01 = new Game(new com.chucknorris.gui.GameInformation(listaP, mapa1, new Dice(0, 6), 20));
		for(int i=0;i<4;i++) {
			try {
				clientSocket = serverSocket.accept();
				System.out.println("Se conecto alguien");
				threads.add(new ClientThread(clientSocket, threads, juego01));
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		GameInformation info = new GameInformation(listaP, mapa1, juego01.getPrecioDolar());
		Gson gson = new Gson();
		String infoSerialized = gson.toJson(info);
		Command startGame = new Command("StartGame", infoSerialized);
		Command habilitarBoton = new Command("TirarDado", "");
		

		for(int i=0 ; i < threads.size();i++) {
			threads.get(i).start();
			threads.get(i).send(startGame, i);
		}
		threads.get(0).send(habilitarBoton,0);


	}
}
