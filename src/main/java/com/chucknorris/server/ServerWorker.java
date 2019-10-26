package com.chucknorris.server;

import com.chucknorris.server.command.ChatMessageCommand;
import com.chucknorris.server.command.Command;
import com.chucknorris.server.command.DoNothingCommand;
import com.chucknorris.server.command.MovePlayerCommand;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ServerWorker extends Thread {
	private final Socket socket;
	private static final String FINISH_COMMAND = "close";
	private static final Command DO_NOTHING_COMMAND = new DoNothingCommand();
	private static final Logger logger = Logger.getLogger(ServerWorker.class.getName());
	private static final Map<String, Command> commandProcessorMap;

	static {
		commandProcessorMap = new HashMap<>();

		commandProcessorMap.put("move_player", new MovePlayerCommand());
		commandProcessorMap.put("chat_message", new ChatMessageCommand());
	}

	public ServerWorker(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			processCommand();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void processCommand() throws IOException, ClassNotFoundException {
//		ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
//
//		ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
//
//		CommandDto commandDto = (CommandDto) inputStream.readObject();

//		logger.log(Level.INFO, String.format("Executing command: %s", commandDto.toString()));

		InputStream inputStream = socket.getInputStream();

		OutputStream outputStream = socket.getOutputStream();

		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

		String command;

		while ((command = reader.readLine()) != null) {
			Command processor = commandProcessorMap.getOrDefault(command, DO_NOTHING_COMMAND);

			processor.process(null, socket);

//			ServerResponse response = processor.process(commandDto.data);
//
//			outputStream.writeUnshared(response);
		}

		socket.close();
	}
}
