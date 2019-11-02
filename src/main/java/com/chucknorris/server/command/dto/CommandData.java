package com.chucknorris.server.command.dto;

import java.net.Socket;
import java.util.Map;

public class CommandData {
	public CommandDto commandDto;

	public CommandData(CommandDto commandDto) {
		this.commandDto = commandDto;
	}

	public String getCommand() {
		return commandDto.command;
	}

	public Socket getSocket() {
		return commandDto.socket;
	}

	public Map getData() {
		return commandDto.data;
	}

	public String getValueAsString(String key) {
		return (String) getData().get(key);
	}
}
