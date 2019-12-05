package com.chucknorris.client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

	private static final int portNumber = 22222;

	public static void main(String args[]) throws Exception {

		try {
			InetAddress ip = InetAddress.getByName("localhost");
			Socket serverSocket = new Socket(ip,portNumber);
			String nombre = "Facu41341";
			ServerLobbyThread escuchador = new ServerLobbyThread(serverSocket, nombre);
			escuchador.start();
			PrintStream ps = new PrintStream(serverSocket.getOutputStream(), true);
			ps.println(nombre);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
