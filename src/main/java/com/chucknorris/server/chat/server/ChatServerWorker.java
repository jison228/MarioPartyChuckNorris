package com.chucknorris.server.chat.server;

import java.io.*;
import java.net.Socket;

public class ChatServerWorker extends Thread {
	private final Socket socket;

	public ChatServerWorker(Socket socket) {
		this.socket = socket;
	}

	private void handleClientConnection(Socket clientSocket) throws IOException {
		InputStream inputStream = clientSocket.getInputStream();
		OutputStream outputStream = clientSocket.getOutputStream();

		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

		String line;
		while ((line = reader.readLine()) != null) {

			if ("quit".equalsIgnoreCase(line)) {
				break;
			}

			String msg = "You typed " + line + "\n";

			outputStream.write(msg.getBytes());
		}

		clientSocket.close();
	}

	@Override
	public void run() {
		try {
			handleClientConnection(socket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
