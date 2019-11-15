package com.chucknorris.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

	private static final int portNumber = 22222;

	public static void main(String args[]) throws Exception {

		try {
			InetAddress ip = InetAddress.getByName("localhost");
			Socket serverSocket = new Socket(ip,portNumber);
			ServerLobbyThread escuchador = new ServerLobbyThread(serverSocket);
			escuchador.start();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
