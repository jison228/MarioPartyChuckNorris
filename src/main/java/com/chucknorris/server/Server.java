package com.chucknorris.server;

import com.chucknorris.server.config.GlobalConfig;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class Server {
	private static final Logger LOGGER = Logger.getLogger(Server.class.getName());
	private static final int SERVER_PORT = GlobalConfig.SERVER_PORT;

	public static void main(String[] args) {
		try {
			attendRequest();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void attendRequest() throws IOException {
		ServerSocket serverSocket = new ServerSocket(SERVER_PORT);

		LOGGER.info(String.format("Listening on port %s", SERVER_PORT));

		while (true) {
			LOGGER.info(String.format("Ready to receive a new client connection in port %s", SERVER_PORT));

			Socket socketClient = serverSocket.accept();

			LOGGER.info(String.format("Accepted connection from %s in port %s", socketClient, SERVER_PORT));

			ServerWorker serverWorker = new ServerWorker(socketClient);

			serverWorker.start();
		}
	}
}