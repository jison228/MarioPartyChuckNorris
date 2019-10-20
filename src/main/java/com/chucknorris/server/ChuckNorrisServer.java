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

			ServerSocket serverSocket = new ServerSocket(GlobalConfig.SERVER_PORT);

			while (true) {
				socket = serverSocket.accept();

				new SocketListener(socket).start();
			}

		} catch (IOException ex) {
			Logger.getLogger(ChuckNorrisServer.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}