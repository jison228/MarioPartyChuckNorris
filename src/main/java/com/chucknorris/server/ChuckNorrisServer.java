package com.chucknorris.server;

import com.chucknorris.server.config.GlobalConfig;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChuckNorrisServer {
	private static final Logger LOGGER = Logger.getLogger(ChuckNorrisServer.class.getName());

	public static void main(String[] args) {
		try {
			Socket socket;

			int serverPort = GlobalConfig.SERVER_PORT;

			ServerSocket serverSocket = new ServerSocket(serverPort);

			LOGGER.log(Level.INFO, String.format("Listening on port %s", serverPort));

			while (true) {
				socket = serverSocket.accept();

				new SocketListener(socket).start();
			}

		} catch (IOException ex) {
			LOGGER.log(Level.SEVERE, ex.toString(), ex);
		}
	}
}