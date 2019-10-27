package com.chucknorris.server.command;

import com.chucknorris.server.command.dto.CommandData;
import com.chucknorris.server.command.response.ChatResponse;
import com.chucknorris.server.command.response.ServerResponse;

import java.util.logging.Level;

public class ChatMessageCommand extends Command<ChatResponse> {

	@Override
	protected ServerResponse execute(CommandData commandData) {
		String message;

		while ((message = readLine()) != null && !message.equals("close")) {

			ChatResponse response = new ChatResponse();

			response.messageToPrint = String.format("%s - %s: %s", System.currentTimeMillis(), "Player", message);

			String serializedResponse = serialize(response);

			writeThroughOutputStream(serializedResponse.getBytes());
		}

		logConnectionFinished();

		return clientCloseConnectionChatResponse(commandData);
	}

	private void logConnectionFinished() {
		String message = String.format("Finishing command chat_message for player %s", "Player dummy");

		LOGGER.log(Level.INFO, message);
	}

	private ServerResponse clientCloseConnectionChatResponse(CommandData commandData) {
		ChatResponse response = new ChatResponse();

		response.messageToPrint = String.format("%s - Player %s has disconnected", getNow(), "PlayerDummy");

		return response;
	}
}
