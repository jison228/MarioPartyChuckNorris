package com.chucknorris.server.command;

import com.chucknorris.server.command.response.ServerResponse;

import java.io.*;
import java.net.Socket;
import java.util.Map;

public class ChatMessageCommand extends Command {

	@Override
	public ServerResponse process(Map commandDataDto, Socket socket) {
		try {
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

			String command;

			while ((command = reader.readLine()) != null && !command.equals("close")) {
				String message = "you type " + command + "\n";
				outputStream.write(message.getBytes());
			}

			String message = "Finishing command chat_message";
			outputStream.write(message.getBytes());

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
