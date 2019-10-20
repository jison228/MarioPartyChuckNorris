package com.chucknorris.server.command;

import com.chucknorris.server.command.response.ServerResponse;

import java.util.Map;

public interface Command {
	ServerResponse process(Map commandDataDto);
}
