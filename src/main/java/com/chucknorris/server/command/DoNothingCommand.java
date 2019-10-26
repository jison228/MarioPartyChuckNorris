package com.chucknorris.server.command;

import com.chucknorris.server.command.response.EmptyResponse;
import com.chucknorris.server.command.response.ServerResponse;

import java.net.Socket;
import java.util.Map;

public class DoNothingCommand extends Command {
	private static final ServerResponse EMPTY_RESPONSE = new EmptyResponse();

	@Override
	public ServerResponse process(Map commandDataDto, Socket socket) {
		System.out.println("Doing nothing");

		return EMPTY_RESPONSE;
	}

}
