package com.chucknorris.server.chat.server;

import com.chucknorris.server.config.GlobalConfig;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServerMain {

	public static void main(String[] args) {
		try {
			while (true) {
				System.out.println("About to receive a new client connection");

				ServerSocket serverSocket = new ServerSocket(GlobalConfig.SERVER_CHAT_PORT);

				Socket socketClient = serverSocket.accept();

				System.out.println("Accepted connection from " + socketClient);

				ChatServerWorker chatServerWorker = new ChatServerWorker(socketClient);

				chatServerWorker.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}