package com.chucknorris.server.command;

import com.chucknorris.server.command.dto.CommandData;
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
		Command command = commandProcessorMap.getOrDefault(commandData.getCommand(), INVALID_COMMAND);

		return command.execute(commandData);
	}
}
