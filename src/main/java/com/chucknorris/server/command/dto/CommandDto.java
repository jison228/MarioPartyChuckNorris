package com.chucknorris.server.command.dto;

import java.io.Serializable;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class CommandDto implements Serializable {
	public String command;
	public Map data = new HashMap();
	public Socket socket;

	@Override
	public String toString() {
		return String.format("%s %s", command, data.toString());
	}
}
