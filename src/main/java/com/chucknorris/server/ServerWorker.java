package com.chucknorris.server;

import com.chucknorris.server.command.BadRequestCommand;
import com.chucknorris.server.command.ChatMessageCommand;
import com.chucknorris.server.command.Command;
import com.chucknorris.server.command.InvalidCommand;
import com.chucknorris.server.command.dto.BadRequestCommandDto;
import com.chucknorris.server.command.dto.CommandDto;
import com.chucknorris.server.command.serializer.Serializer;
import com.chucknorris.server.command.serializer.SerializerImpl;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ServerWorker extends Thread {
	private static final String FINISH_COMMAND = "close";
	private static final Command INVALID_COMMAND = new InvalidCommand();
	private static final Logger LOGGER = Logger.getLogger(ServerWorker.class.getName());
	private static final Serializer<CommandDto> serializer = new SerializerImpl<>();
	private static final Map<String, Command> commandProcessorMap;

	static {
		commandProcessorMap = new HashMap<>();

		commandProcessorMap.put("chat_message", new ChatMessageCommand());
		commandProcessorMap.put("bad_request", new BadRequestCommand());
	}

	private final Socket socket;

	public ServerWorker(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			processCommand();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void processCommand() throws IOException {
		InputStream inputStream = socket.getInputStream();

		OutputStream outputStream = socket.getOutputStream();

		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

		CommandDto commandDto;

		while ((commandDto = getCommand(reader.readLine())) != null && !FINISH_COMMAND.equals(commandDto.command)) {

			if (StringUtils.isEmpty(commandDto.command)) {
				commandDto.command = "invalid_command";
			}

			LOGGER.info("Processing command " + commandDto.toString());

			Command processor = commandProcessorMap.getOrDefault(commandDto.command, INVALID_COMMAND);

			String response = processor.process(commandDto, socket);

			outputStream.write(response.getBytes());
		}

		reader.close();

		socket.close();
	}

	private CommandDto getCommand(String json) {
		try {
			return serializer.serialize(json, CommandDto.class);
		} catch (Exception ex) {
			return new BadRequestCommandDto();
		}
	}
}
