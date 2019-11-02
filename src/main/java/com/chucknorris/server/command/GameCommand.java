package com.chucknorris.server.command;

import com.chucknorris.server.command.dto.CommandData;
import com.chucknorris.server.command.dto.CommandDto;
import com.chucknorris.server.command.game.MovePlayerCommand;
import com.chucknorris.server.command.response.ServerResponse;

import java.util.HashMap;
import java.util.Map;

public class GameCommand extends Command {
	private static final Command INVALID_COMMAND = new InvalidCommand();
	private final Map<String, Command> commandProcessorMap;

	public GameCommand() {
		this.commandProcessorMap = new HashMap<>();

		commandProcessorMap.put("move_player", new MovePlayerCommand());
	}

	@Override
	protected ServerResponse execute(CommandData commandData) throws Throwable {

		CommandDto commandDto = new CommandDto();
		commandDto.socket = commandData.getSocket();
		commandDto.command = (String) commandData.getData().get("command");
		commandDto.data = (Map) commandData.getData().get("data");

		CommandData gameCommandData = new CommandData(commandDto);

		Command command = commandProcessorMap.getOrDefault(gameCommandData.getCommand(), INVALID_COMMAND);

		return command.execute(gameCommandData);
	}
}
