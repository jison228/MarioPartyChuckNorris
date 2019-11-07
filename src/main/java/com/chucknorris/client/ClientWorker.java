package com.chucknorris.client;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.chucknorris.server.ServerWorker;
import com.chucknorris.server.command.Command;
import com.chucknorris.server.command.dto.CommandDto;
import com.chucknorris.server.command.serializer.Serializer;
import com.chucknorris.server.command.serializer.SerializerImpl;

public class ClientWorker extends Thread{
	private static final Logger LOGGER = Logger.getLogger(ServerWorker.class.getName());
	private static final Serializer<CommandDto> serializer = new SerializerImpl<>();
	private final Map<String, Command> commandProcessorMap;
	
	private final Socket socket;
	
	public ClientWorker(Socket socket) {
		this.socket = socket;
		
		commandProcessorMap = new HashMap<>();
	}
	
}
