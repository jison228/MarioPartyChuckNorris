package com.chucknorris.server.command;

import com.chucknorris.server.command.response.EmptyResponse;
import com.chucknorris.server.command.response.ServerResponse;

import java.util.Map;

public class DoNothingCommand implements Command {
	private static final ServerResponse EMPTY_RESPONSE = new EmptyResponse();

	@Override
	public ServerResponse process(Map commandDataDto) {
		return EMPTY_RESPONSE;
	}

}
