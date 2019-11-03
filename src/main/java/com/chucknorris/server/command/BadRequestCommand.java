package com.chucknorris.server.command;

import com.chucknorris.server.command.dto.CommandData;
import com.chucknorris.server.command.response.BadRequestResponse;
import com.chucknorris.server.command.response.ServerResponse;

public class BadRequestCommand extends Command {
	private static final ServerResponse BAD_REQUEST_RESPONSE = new BadRequestResponse();

	@Override
	protected ServerResponse execute(CommandData commandData) {
		return BAD_REQUEST_RESPONSE;
	}
}
