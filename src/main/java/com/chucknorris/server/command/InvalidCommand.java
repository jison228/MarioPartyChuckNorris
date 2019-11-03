package com.chucknorris.server.command;

import com.chucknorris.server.command.dto.CommandData;
import com.chucknorris.server.command.response.BadCommandResponse;
import com.chucknorris.server.command.response.ServerResponse;

public class InvalidCommand extends Command {

	@Override
	protected ServerResponse execute(CommandData commandData) {
		return new BadCommandResponse(commandData.getCommand());
	}
}
