package com.chucknorris.server;

import com.chucknorris.server.command.Command;
import com.chucknorris.server.command.DoNothingCommand;
import com.chucknorris.server.command.MovePlayerCommand;
import com.chucknorris.server.command.dto.CommandDto;
import com.chucknorris.server.command.response.ServerResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketListener extends Thread {
	private static final String FINISH_COMMAND = "close";
	private static final Command DO_NOTHING_COMMAND = new DoNothingCommand();
	private static final Logger logger = Logger.getLogger(SocketListener.class.getName());
	private static final Map<String, Command> commandProcessorMap;

	static {
		commandProcessorMap = new HashMap<>();

		commandProcessorMap.put("move_player", new MovePlayerCommand());
	}

	private ObjectOutputStream output;
	private ObjectInputStream input;

	public SocketListener(Socket socket) throws IOException {
		output = new ObjectOutputStream(socket.getOutputStream());

		input = new ObjectInputStream(socket.getInputStream());
	}

	@Override
	public void run() {
		try {
			processCommand();
		} catch (IOException | ClassNotFoundException ex) {
			logger.log(Level.SEVERE, null, ex);
		}
	}

	private void processCommand() throws IOException, ClassNotFoundException {
		CommandDto commandDto = (CommandDto) input.readObject();

		logger.log(Level.INFO, String.format("Executing command: %s", commandDto.toString()));

		while (!commandDto.command.equals(FINISH_COMMAND)) {
			Command processor = commandProcessorMap.getOrDefault(commandDto.command, DO_NOTHING_COMMAND);

			ServerResponse response = processor.process(commandDto.data);

			output.writeUnshared(response);
		}
	}
}
