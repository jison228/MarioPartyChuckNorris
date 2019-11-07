package com.chucknorris.client.command;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Logger;

import com.chucknorris.client.command.response.ClientResponse;
import com.chucknorris.server.command.dto.CommandData;
import com.chucknorris.server.command.dto.CommandDto;
import com.chucknorris.server.command.serializer.Serializer;
import com.chucknorris.server.command.serializer.SerializerImpl;

public abstract class Command <T extends ClientResponse>{
	protected static final Logger LOGGER = Logger.getLogger(Command.class.getName());

	private Serializer<T> serializer = new SerializerImpl<>();
	//TODO Cuando cerramos el ¿reader?
	private BufferedReader reader;
	private InputStream inputStream;

	protected abstract ClientResponse execute(CommandData commandData) throws Throwable;

	public final String process(CommandDto commandDto, Socket socket) {
		try {
			ClientResponse response = handleCommand(commandDto, socket);

			return serializer.serialize((T) response);
		} catch (Throwable e) {
			e.printStackTrace();

			//TODO fallback
			return "";
		}
	}
	
	protected String readLine() {
		if (reader == null) {
			reader = new BufferedReader(new InputStreamReader(inputStream));
		}

		try {
			return reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//Todo return fallback string
		return "";
	}
	
	private ClientResponse handleCommand(CommandDto commandDto, Socket socket) throws Throwable {
		inputStream = socket.getInputStream();
		
		CommandData commandData = new CommandData();
		commandData.commandDto = commandDto;
		
		return execute(commandData);
	}
}
