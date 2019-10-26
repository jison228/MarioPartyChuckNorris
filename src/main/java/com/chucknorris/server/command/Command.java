package com.chucknorris.server.command;

import com.chucknorris.server.command.response.ServerResponse;

import java.net.Socket;
import java.util.Map;

public abstract class Command {
	public abstract ServerResponse process(Map commandDataDto, Socket socket);
}
