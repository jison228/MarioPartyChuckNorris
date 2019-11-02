package com.chucknorris.server;

import com.chucknorris.server.command.*;
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
	private final Map<String, Command> commandProcessorMap;

	private final Socket socket;

	public ServerWorker(Socket socket) {
		this.socket = socket;

		commandProcessorMap = new HashMap<>();

		commandProcessorMap.put("chat_message", new ChatMessageCommand());
		commandProcessorMap.put("game_command", new GameCommand());
		commandProcessorMap.put("bad_request", new BadRequestCommand());
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

		LOGGER.info("Ready for process command from " + socket);

		while ((commandDto = getCommand(reader.readLine())) != null && !FINISH_COMMAND.equals(commandDto.command)) {

			if (StringUtils.isEmpty(commandDto.command)) {
				commandDto.command = "bad_request";
			}

			//TODO esto no esta bueno.
			commandDto.socket = socket;

			LOGGER.info("Processing command " + commandDto.toString());

			Command processor = commandProcessorMap.getOrDefault(commandDto.command, INVALID_COMMAND);

			LOGGER.info(String.format("Command processor '%s' assigned to '%s' command for socket '%s'", processor.getProcessorName(), commandDto.command, socket));

			String response = processor.process(commandDto);

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
